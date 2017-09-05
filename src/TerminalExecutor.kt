import com.intellij.openapi.util.SystemInfo
import java.io.File


object TerminalExecutor {

    private val MAC_TERMINAL = "/Applications/Utilities/Terminal.app/Contents/MacOS/Terminal"
    private val ITERM = "/Applications/iTerm.app/Contents/MacOS/iTerm"
    private val ITERM2 = "/Applications/iTerm.app/Contents/MacOS/iTerm2"
    private val WIN_CMD = "C:\\Windows\\System32\\cmd.exe"

    fun Execute(targetPath: String)
    {
        val path = when {
            SystemInfo.isMac -> {
                when{
                    isTerminalInstalled(ITERM) -> ITERM
                    isTerminalInstalled(ITERM2) -> ITERM2
                    else -> MAC_TERMINAL
                }
            }
            SystemInfo.isWindows -> WIN_CMD
            else -> throw OperatingSystemNotSupported()
        }
        val cmdArr = arrayOf(path, targetPath)

        Runtime.getRuntime().exec(cmdArr)


    }

    private fun isTerminalInstalled(terminalPath: String): Boolean {
        val terminal = File(terminalPath)
        return terminal.exists() && terminal.canExecute()
    }

}