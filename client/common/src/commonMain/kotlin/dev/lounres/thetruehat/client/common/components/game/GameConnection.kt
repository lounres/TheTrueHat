package dev.lounres.thetruehat.client.common.components.game

import dev.lounres.thetruehat.api.ClientSignal
import dev.lounres.thetruehat.api.ServerSignal
import dev.lounres.thetruehat.client.common.utils.catchConnectionExceptionsAndRepeat
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel


public class GameConnection(
    public val coroutineScope: CoroutineScope,
    public val httpClient: HttpClient,
    public val host: String? = null,
    public val port: Int? = null,
    public val path: String? = null,
    public val retryPeriod: Long,
    public val onConnect: suspend DefaultClientWebSocketSession.() -> Unit,
    public val onConnectionFailure: suspend () -> Unit,
): AutoCloseable {
    private val _incoming: Channel<ServerSignal> = Channel(capacity = Channel.UNLIMITED)
    public val incoming: ReceiveChannel<ServerSignal> = _incoming
    private val _outgoing: Channel<ClientSignal> = Channel(capacity = Channel.UNLIMITED)
    public val outgoing: SendChannel<ClientSignal> = _outgoing

    public val connectionJob: Job = coroutineScope.launch {
        while (isActive) {
            catchConnectionExceptionsAndRepeat(
                tryBlock = {
                    httpClient.ws(host = host, port = port, path = path) {
                        onConnect()
                        val incomingJob = launch {
                            for (frame in incoming) {
                                val converter = converter!!

                                if (!converter.isApplicable(frame)) continue

                                val signal = converter.deserialize<ServerSignal>(content = frame)
                                println("Incoming signal: $signal")
                                _incoming.send(signal)
                            }
                        }
                        val outgoingJob = launch {
                            for (signal in _outgoing) sendSerialized<ClientSignal>(signal)
                        }
                        listOf(incomingJob, outgoingJob).joinAll()
                    }
                },
                catchBlock = { exception ->
                    println(exception)
                    onConnectionFailure()
                }
            )
            delay(retryPeriod)
        }
    }

    override fun close() {
        connectionJob.cancel()
    }
}