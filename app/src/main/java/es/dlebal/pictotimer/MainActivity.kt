package es.dlebal.pictotimer

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Class class MainActivity : AppCompatActivity()
 *
 * This class provides MainActivity objects
 */
class MainActivity : AppCompatActivity() {

    /**
     * Constants
     */
    private val className: String = MainActivity::class.java.simpleName // Class name

    /**
     * Variables
     */
    private lateinit var toolbar: ActionBar // Toolbar
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_tasks -> { // Tasks
                this.toolbar.title = getString(R.string.tasks)
                this.openFragment(TasksFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_journal -> { // Journal
                this.toolbar.title = getString(R.string.journal)
                this.openFragment(JournalFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> { // Profile
                this.toolbar.title = getString(R.string.profile)
                this.openFragment(ProfileFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    } // OnNavigationItemSelectedListener

    /**
     * Method override fun onCreate(savedInstanceState: Bundle?)
     *
     * Performs the following tasks:
     *   - Initialize the activity
     *   - Initialize the UI
     *
     * Parameters:
     *   @param savedInstanceState: If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Constants
        val methodName = "onCreate" // Method name

        // Initialize the activity
        Log.d(this.className + ": $methodName", "Initialize the activity")
        setContentView(R.layout.activity_main)

        // Initialize the UI
        Log.d(this.className + ": $methodName", "Initialize the UI")
        this.initializeUI()

    }

    /**
     * Method private fun initializeUI()
     *
     * Performs the following tasks:
     *   - Initialize the tool bar
     *   - Initialize the navigation
     *   - Open the tasks fragment
     */
    private fun initializeUI() {

        // Constants
        val methodName = "initializeUI" // Method name

        // Initialize the tool bar
        Log.d(this.className + ": $methodName", "Initialize the tool bar")
        this.toolbar = supportActionBar!!

        // Initialize the navigation
        Log.d(this.className + ": $methodName", "Initialize the navigation")
        navigation.setOnNavigationItemSelectedListener(this.onNavigationItemSelectedListener)

        // Open the tasks fragment
        Log.d(this.className + ": $methodName", "Open the tasks fragment")
        this.openFragment(TasksFragment.newInstance())

    }

    /**
     * Method private fun openFragment(fragment: Fragment)
     *
     * Performs the following tasks:
     *   - Open a fragment
     *
     * Parameters:
     *   @param fragment: Fragment
     */
    private fun openFragment(fragment: Fragment) {

        // Constants
        val methodName = "openFragment" // Method name

        // Open a fragment
        Log.d(this.className + ": $methodName", "Open a fragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .addToBackStack(fragment.javaClass.getSimpleName())
            .commit()

    }

}
