package es.dlebal.pictotimer.app

import android.util.Log
import java.util.concurrent.TimeUnit

/**
 * Class class Util
 *
 * This class provides Util objects
 */
class Util {

    companion object {

        /**
         * Constants
         */
        private val className: String = Util::class.java.simpleName // Class name

        /**
         * Method fun timerFormatter(milliseconds: Long): String
         *
         * Performs the following tasks:
         *   - Return the result of convert milliseconds to time format
         *
         * Parameters:
         *   @param milliseconds: Milliseconds
         *   @return String: Time format
         */
        fun timerFormatter(milliseconds: Long): String {

            // Constants
            val methodName = "timerFormatter" // Method name

            // Return the result of convert milliseconds to time format
            Log.d(this.className + ": $methodName", "Return the result of convert milliseconds to time format")
            return String.format(
                "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliseconds),
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(
                        milliseconds
                    )
                ),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(
                        milliseconds
                    )
                )
            )

        }

    }

}
