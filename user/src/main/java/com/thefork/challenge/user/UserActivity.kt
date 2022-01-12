package com.thefork.challenge.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.thefork.challenge.api.UserPreview
import com.thefork.challenge.user.adapter.UserInformationAdapter
import com.thefork.challenge.user.adapter.UserInformationRowType
import com.thefork.challenge.user.databinding.ActivityUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityUserBinding
    private val contactInformation: MutableList<UserInformationRowType> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.toolbarProfile.title = getString(R.string.user_toolbar)
        setSupportActionBar(viewBinding.toolbarProfile)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        intent?.let {
            it.getParcelableExtra<UserPreview>("USER")?.let { user ->
                viewBinding.userName.text = user.firstName.plus(" ").plus(user.lastName)
                setContactInformation(user)
                setUserPictureProfile(user)
                viewBinding.userInformationRecyclerView
                    .adapter = UserInformationAdapter(contactInformation.toList())
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUserPictureProfile(user: UserPreview) {
        CoroutineScope(Dispatchers.Main).launch {
            Glide.with(this@UserActivity)
                .load(BACKGROUND_URL)
                .into(viewBinding.userBannerBackground)

            Glide.with(this@UserActivity)
                .load(user.picture)
                .transform(CircleCrop())
                .into(viewBinding.userPicture)
        }
    }

    private fun setContactInformation(user: UserPreview) {
        with(user) {
            if (email.isNotEmpty() || email.isNotBlank()) {
                contactInformation.add(UserInformationRowType.EmailRow(email))
                contactInformation.add(UserInformationRowType.AlternativeEmailRow(email))
            }
            if (phone.isNotEmpty() || phone.isNotBlank()) {
                contactInformation.add(UserInformationRowType.WorkNumberRow(phone))
                contactInformation.add(UserInformationRowType.MobileNumberRow(phone))
            }
        }
    }

    private companion object {
        const val BACKGROUND_URL =
            "https://i.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68"
    }

}
