package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  ProfileFragment.OnFragmentInteractionListener, RegistroFragment.OnFragmentInteractionListener
        , KitFragment.OnFragmentInteractionListener{

    private TextView textFavorites;
    private TextView textSchedules;
    private TextView textMusic;
    private View viewLayout;
    private static final String TAG = "BottomBarDemo";
    private int[] bottomBarColors;
    private static final String TAG_PERFIL = "perfil";
    private static final String TAG_REGISTRO = "registro";
    private static final String TAG_KIT = "kit";
   // private static final String TAG_SUBJECTS = "subjects";
   // private static final String TAG_CALENDAR = "calendar";
    public static String CURRENT_TAG = TAG_REGISTRO;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    public static int navItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            //set the transition
            Transition ts = new Explode();
            ts.setDuration(5000);
            getWindow().setEnterTransition(ts);
            getWindow().setExitTransition(ts);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_registro:
                                navItemIndex = 0;
                                CURRENT_TAG = TAG_REGISTRO;
                                break;
                            case R.id.action_kit:
                                navItemIndex = 1;
                                CURRENT_TAG = TAG_KIT;
                                break;
                            case R.id.action_despensa:

                                break;
                            case R.id.action_estadistica:

                                break;
                            case R.id.action_perfil:
                                navItemIndex = 4;
                                CURRENT_TAG = TAG_PERFIL;
                                break;
                        }
                        loadHomeFragment();
                        return true;
                    }
                });

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_REGISTRO;
            loadHomeFragment();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                RegistroFragment registroFragment = new RegistroFragment();
                return registroFragment;
            case 1:
                // home
                KitFragment kitFragment = new KitFragment();
                return kitFragment;
            case 4:
                // home
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
           /* case 1:
                // subjects
                SubjectFragment subjectFragment = new SubjectFragment();
                return subjectFragment;
            case 2:
                // subjects
                CalendarFragment calendarFragment = new CalendarFragment();
                return calendarFragment;
                */
            default:
                return new RegistroFragment();
        }
    }

    private void loadHomeFragment() {

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

}
