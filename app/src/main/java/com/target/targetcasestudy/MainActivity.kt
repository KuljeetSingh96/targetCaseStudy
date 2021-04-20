package com.target.targetcasestudy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.ui.deallist.DealListFragment
import com.target.targetcasestudy.ui.dealsdetail.DealDetailFragment
import com.target.targetcasestudy.ui.payment.PaymentDialogFragment

class MainActivity : AppCompatActivity(), DealDetailFragment.DealDetailFragmentInteractionListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportFragmentManager.beginTransaction()
      .replace(R.id.container,
          DealListFragment()
      )
      .commit()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }
  override fun onBackPressed() {
    val count = supportFragmentManager.backStackEntryCount
    if (count == 0) {
      super.onBackPressed()
    } else {
      supportFragmentManager.popBackStack()
    }
  }
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.credit_card -> {
        PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
        true
      }
      else -> false
    }
  }

  override fun finishFragment() {
    onBackPressed()
  }

}
