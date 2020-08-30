package com.zubisofts.gadsleaderboard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zubisofts.gadsleaderboard.R;
import com.zubisofts.gadsleaderboard.adapters.GADViewpagerAdapter;
import com.zubisofts.gadsleaderboard.fragments.LearningLeadersFragment;
import com.zubisofts.gadsleaderboard.fragments.SkillIQLeadersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tablayout);

        GADViewpagerAdapter gadViewpagerAdapter = new GADViewpagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        gadViewpagerAdapter.addFragment(new LearningLeadersFragment(), "Learning Leaders");
        gadViewpagerAdapter.addFragment(new SkillIQLeadersFragment(), "Skill IQ Leaders");

        viewPager.setAdapter(gadViewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        findViewById(R.id.btnSubmission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProjectSubmissionActivity.class));
            }
        });

    }
}