package kmp.template.foundation.usecase

/**
 * Use case interface - represents business logic unit operation.
 *
 * @param IN input data type
 * @param OUT output data type
 */
interface UseCase<IN : Any, OUT : Any> {
    suspend operator fun invoke(input: IN): OUT
}

/**
 * Use case extension function - invokes use case without input data.
 *
 * @param OUT output data type
 */
suspend operator fun <OUT : Any> UseCase<Unit, OUT>.invoke(): OUT = invoke(Unit)
