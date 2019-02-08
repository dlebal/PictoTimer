package es.dlebal.pictotimer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.Toast
import es.dlebal.pictotimer.adapters.CoverFlowAdapter
import es.dlebal.pictotimer.app.Util.Companion.timerFormatter
import es.dlebal.pictotimer.models.Task
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow

/**
 * Class class TasksFragment : Fragment()
 *
 * This class provides TasksFragment objects
 */
class TasksFragment : Fragment() {

    /**
     * Constants
     */
    private val className: String = TasksFragment::class.java.simpleName // Class name

    /**
     * Variables
     */
    private lateinit var coverFlowAdapter: CoverFlowAdapter // CoverFlowAdapter
    private lateinit var tasks: ArrayList<Task> // Tasks

    /**
     * companion object
     */
    companion object {

        /**
         * Method fun newInstance(): TasksFragment = TasksFragment()
         *
         * Performs the following tasks:
         *   - Return a new instance of fragment TasksFragment
         *
         * Parameters:
         *   @return TasksFragment: New instance of fragment TasksFragment
         */
        fun newInstance(): TasksFragment = TasksFragment()

    }

    /**
     * Method override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
     *
     * Performs the following tasks:
     *   - Return the view for the fragment's UI
     *
     * Parameters:
     *   @param inflater: The LayoutInflater object that can be used to inflate any views in the fragment
     *   @param container: If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view
     *   @param savedInstanceState: If non-null, this fragment is being re-constructed from a previous saved state as given here
     *   @return View: Return the View for the fragment's UI, or null
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Constants
        val methodName = "onCreateView" // Method name

        // Return the view for the fragment's UI
        Log.d(this.className + ": $methodName", "Return the view for the fragment's UI")
        return inflater.inflate(R.layout.fragment_tasks, container, false)

    }

    /**
     * Method override fun onViewCreated(view: View, savedInstanceState: Bundle?)
     *
     * Performs the following tasks:
     *   - Set tasks
     *   - Initialize the task name
     *   - Initialize the coverflow
     *   - Initialize the task timer
     *
     * Parameters:
     *   @param view: The View returned by onCreateView
     *   @param savedInstanceState: If non-null, this fragment is being re-constructed from a previous saved state as given here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        // Constants
        val methodName = "onViewCreated" // Method name

        // Variables
        val taskName = view.findViewById(R.id.taskName) as? TextSwitcher
        val coverflow = view.findViewById(R.id.coverflow) as? FeatureCoverFlow
        val taskTimer = view.findViewById(R.id.taskTimer) as? TextSwitcher

        // Set tasks
        Log.d(this.className + ": $methodName", "Set tasks")
        setTasks()

        // Initialize the task name
        Log.d(this.className + ": $methodName", "Initialize the task name")
        taskName?.setFactory {
            val inflater = LayoutInflater.from(context)
            inflater.inflate(R.layout.item_task_name, null) as TextView
        }
        taskName?.inAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom)
        taskName?.outAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_top)

        // Initialize the coverflow
        Log.d(this.className + ": $methodName", "Initialize the coverflow")
        this.coverFlowAdapter = CoverFlowAdapter(context!!, this.tasks)
        coverflow?.adapter = this.coverFlowAdapter
        coverflow?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(context, resources.getString(this.tasks[position].pictogram), Toast.LENGTH_SHORT).show()
        }
        coverflow?.setOnScrollPositionListener(object : FeatureCoverFlow.OnScrollPositionListener {

            /**
             * Method override fun onScrolledToPosition(position: Int)
             *
             * Performs the following tasks:
             *   - Set the task data
             *
             * Parameters:
             *   @param position: The position of the task
             */
            override fun onScrolledToPosition(position: Int) {

                // Constants
                val submethodName = "onScrolledToPosition" // Method name

                // Set the task data
                Log.d(this@TasksFragment.className + ": $methodName: $submethodName", "Set the task data")
                taskName?.setText(this@TasksFragment.tasks[position].name)
                taskTimer?.setText(timerFormatter(this@TasksFragment.tasks[position].timer))

            }

            /**
             * Method override fun onScrolling()
             *
             * Performs the following tasks:
             *   - Clear the task data
             */
            override fun onScrolling() {

                // Constants
                val submethodName = "onScrolling" // Method name

                // Clear the task data
                Log.d(this@TasksFragment.className + ": $methodName: $submethodName", "Clear the task data")
                taskName?.setText("")
                taskTimer?.setText("")

            }

        })

        // Initialize the task timer
        Log.d(this.className + ": $methodName", "Initialize the task timer")
        taskTimer?.setFactory {
            val inflater = LayoutInflater.from(context)
            inflater.inflate(R.layout.item_task_timer, null) as TextView
        }
        taskTimer?.inAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_top)
        taskTimer?.outAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom)

    }

    /**
     * Method private fun setTasks()
     *
     * Performs the following tasks:
     *   - Set the tasks
     */
    private fun setTasks() {

        // Constants
        val methodName = "setTasks" // Method name

        // Set the tasks
        Log.d(this.className + ": $methodName", "Set the tasks")
        this.tasks = ArrayList()
        this.tasks.add(Task(1,"Tareas", R.drawable.picto_task, 600000))
        this.tasks.add(Task(2,"Lavar dientes", R.drawable.picto_wash_teeth, 180000))
        this.tasks.add(Task(3,"Zumba", R.drawable.picto_zumba, 1800000))
        this.tasks.add(Task(4,"Ir al baño", R.drawable.picto_do_poop, 300000))

    }

}
