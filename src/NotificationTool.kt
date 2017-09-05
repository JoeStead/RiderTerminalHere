import com.intellij.notification.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project

/**
 * Author: BobZhao
 * Date:   12/2/15 18:31
 * Description: tool for notification
 */
object NotificationTool {

    /**
     * notify message via Notification Bus component
     * @param project
     * @param title
     * @param message
     * @param notificationType
     */
    fun notify(project: Project, title: String, message: String, notificationType: NotificationType) {
        ApplicationManager.getApplication().runWriteAction {
            val groupId = OpenTerminalHereAction.PLUGIN_NAME
            NotificationsConfiguration.getNotificationsConfiguration().register(groupId, NotificationDisplayType.BALLOON, false)
            val notification = Notification(groupId, title, message,
                    notificationType, null)
            Notifications.Bus.notify(notification, project)
        }
    }
}
