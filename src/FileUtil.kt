import com.intellij.ide.projectView.ProjectView
import com.intellij.ide.util.treeView.AbstractTreeNode
import com.intellij.ide.util.treeView.NodeDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.jetbrains.rider.projectView.solutionExplorer.SolutionExplorerNodeBase


/**
 * Author: BobZhao
 * Date:   12/2/15 18:31
 * Description: tool for file operation
 */
object FileUtil {

    /**
     * determine the VirtualFile which selected in Project view
     * @param project
     * @return
     */
    fun getSelectedProjectFile(project: Project): VirtualFile? {

        val currentProjectViewPane = ProjectView.getInstance(project).currentProjectViewPane ?: return null
        val node = currentProjectViewPane.selectedNode ?: return null
        var selected: Any? = null
        val userObject = node.userObject
        if (userObject is AbstractTreeNode<*>) {
            selected = userObject.value
        } else if (userObject is NodeDescriptor<*>) {
            selected = userObject.element
        }
        if(userObject is SolutionExplorerNodeBase)
        {
            selected = userObject.virtualFile
        }

        if (selected == null) {
            return null
        }

        var vf: VirtualFile? = null
        when (selected) {
            is PsiDirectory -> vf = selected.virtualFile
            is PsiElement -> vf = selected.containingFile.virtualFile.parent
            is VirtualFile -> vf = selected.parent
            else -> {
                // ignored
            }
        }

        return vf
    }


}
