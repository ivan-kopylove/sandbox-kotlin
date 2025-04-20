package com.github.ivan.kopylove.kotlin.sandbox.faabb50d52204887a1bbbe918f6bcbf2

/**
 * @see <a href="obsidian://search/?vault=notes&query=faabb50d">obsidian</a>
 * @see <a href="https://ivan-kopylove.github.io/faabb50d-5220-4887-a1bb-be918f6bcbf2">blog</a>
*/
class Solution {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val stack = ArrayDeque<TreeNode?>()
        val collector = mutableListOf<Int>()
        var node = root

        while(node != null || stack.isNotEmpty())
        {
            if(node != null)
            {
                collector.add(node.`val`)
                stack.addFirst(node)
                node = node.right
            }
            else {
                val popped = stack.firstOrNull()
                node = popped?.left
                stack.removeFirst()
            }
        }
        return collector.reversed()
    }
}