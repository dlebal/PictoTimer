package es.dlebal.pictotimer.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import es.dlebal.pictotimer.R
import es.dlebal.pictotimer.models.Task
import java.util.*

/**
 * Class class CoverFlowAdapter(context: Context) : BaseAdapter()
 *
 * This class provides CoverFlowAdapter objects
 *
 * Properties:
 *   - context: Context
 *   - tasks: Tasks
 */
class CoverFlowAdapter(context: Context, tasks: ArrayList<Task>) : BaseAdapter() {

    /**
     * Constants
     */
    private val className: String = CoverFlowAdapter::class.java.simpleName // Class name

    /**
     * Variables
     */
    private val inflater: LayoutInflater = LayoutInflater.from(context) // LayoutInflater
    private var tasks = ArrayList<Task>(0) // Tasks

    /**
     * init
     */
    init {
        this.tasks = tasks
    }

    /**
     * Method override fun getCount(): Int
     *
     * Performs the following tasks:
     *   - Return the count of tasks
     *
     * Parameters:
     *   @return this.tasks.size: Count of tasks
     */
    override fun getCount(): Int {

        // Constants
        val methodName = "getCount" // Method name

        // Return the count of tasks
        Log.d(this.className + ": $methodName", "Return the count of tasks")
        return this.tasks.size

    }

    /**
     * Method override fun getItem(position: Int): Any
     *
     * Performs the following tasks:
     *   - Return the task at the specified position
     *
     * Parameters:
     *   @param position: The position of the task whose data we want within the adapter's data set
     *   @return this.tasks[position]: Task at the specified position
     */
    override fun getItem(position: Int): Any {

        // Constants
        val methodName = "getItem" // Method name

        // Return the task at the specified position
        Log.d(this.className + ": $methodName", "Return the task at the specified position")
        return this.tasks[position]

    }

    /**
     * Method override fun getItemId(position: Int): Long
     *
     * Performs the following tasks:
     *   - Return the identifier of the task at the specified position
     *
     * Parameters:
     *   @param position: The position of the task within the adapter's data set whose row identifier we want
     *   @return position.toLong(): Identifier of the task at the specified position
     */
    override fun getItemId(position: Int): Long {

        // Constants
        val methodName = "getItemId" // Method name

        // Return the identifier of the task at the specified position
        Log.d(this.className + ": $methodName", "Return the identifier of the task at the specified position")
        return position.toLong()

    }

    /**
     * Method override fun getView(position: Int, convertView: View?, parent: ViewGroup): View?
     *
     * Performs the following tasks:
     *   - Return a view that displays the task at the specified position in the data set
     *
     * Parameters:
     *   @param position: The position of the task within the adapter's data set of the item whose view we want
     *   @param convertView: The old view to reuse, if possible
     *   @param parent: The parent that this view will eventually be attached to
     *   @return view: View corresponding to the task at the specified position
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        // Constants
        val methodName = "getView" // Method name

        // Variables
        val view: View? // View
        val taskViewHolder: TaskViewHolder // TaskViewHolder


        // Return a view that displays the task at the specified position in the data set
        Log.d(this.className + ": $methodName", "Return a view that displays the task at the specified position in the data set")
        if (convertView == null) {
            view = this.inflater.inflate(R.layout.item_coverflow, parent, false)
            taskViewHolder = TaskViewHolder(view)
            view.tag = taskViewHolder
        } else {
            view = convertView
            taskViewHolder = view.tag as TaskViewHolder
        }
        taskViewHolder.taskName.text = this.tasks[position].name
        taskViewHolder.taskPictogram.setImageResource(this.tasks[position].pictogram)
        taskViewHolder.taskTimer.text = this.tasks[position].timer.toString()
        return view

    }

    /**
     * Class private class TaskViewHolder(view: View?)
     *
     * This class provides TaskViewHolder objects
     *
     * Properties:
     *   - view: View
     */
    private class TaskViewHolder(view: View?) {

        /**
         * Variables
         */
        val taskName: TextView = view?.findViewById(R.id.taskName) as TextView // Task name
        val taskPictogram: ImageView = view?.findViewById(R.id.taskPictogram) as ImageView // Task pictogram
        val taskTimer: TextView = view?.findViewById(R.id.taskTimer) as TextView // Task timer

    }

}
