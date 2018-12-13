package automaton.regular.displayer

import java.util.*


open class DisplayedManager : Displayer {

    interface DisplayOption
    private enum class EmptyOption : DisplayOption

    protected open val optionMessage: HashMap<DisplayOption, String> = hashMapOf()
    protected open val action: HashMap<DisplayOption, () -> Unit> = hashMapOf()

    private fun displayMenu() =
            optionMessage.values.sorted().forEach { println(it) }

    protected open val getUserOptionFrom =
            fun(index: Int): DisplayOption = EmptyOption.values()[index - 1]

    private fun executeOption(option: DisplayOption)
            = action[option]?.invoke()

    private fun read(): Int =
            print("Option: ").let { Scanner(System.`in`).nextInt() }

    override fun start() =
            displayMenu().also { executeOption(getUserOptionFrom(read())) }

}
