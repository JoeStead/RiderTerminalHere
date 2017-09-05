import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import java.io.IOException


class OpenTerminalHereAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        
        val project = event.project ?: return

        perform(project)

    }

    /**
     * perform the action
     * @param project
     */
    private fun perform(project: Project) {

        val selectedFile = FileUtil.getSelectedProjectFile(project) ?: return

        val targetPath = selectedFile.path
        
        try {
            TerminalExecutor.Execute(targetPath)
        }
        catch(e: OperatingSystemNotSupported) {
            NotificationTool.notify(project, PLUGIN_NAME,
                    "Your operating system is not supported.", com.intellij.notification.NotificationType.ERROR)

        }
        catch(e: IOException) {
            NotificationTool.notify(project, PLUGIN_NAME,
                    "Terminal Failed to start ${e.message}", com.intellij.notification.NotificationType.ERROR)

        }

    }

    companion object {

        val PLUGIN_NAME = "OpenTerminalHere"
    }


}
