package com.ahsan.wallpaper.manager;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AppFragmentManager {
    private static AppFragmentManager appFragmentManager = new AppFragmentManager();

    private FragmentManager fragmentManager;
    private int fragmentContainerViewId;

    private AppFragmentManager() {
    }

    public static AppFragmentManager sharedInstance() {
        return appFragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        //setListenerBackStack();
    }

    private void setListenerBackStack(){
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                fragmentManager.getBackStackEntryCount();
            }
        });
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentContainerViewId(int fragmentContainerViewId) {
        this.fragmentContainerViewId = fragmentContainerViewId;
    }

    /**
     * @param fragment A fragment to replace all the added fragments in backstack with
     *                 Clears prior added fragments to replace with the new one.
     */

    public void replaceFragmentContainerWithFragment(Fragment fragment) {

//        clearFragmentStack();

        replaceFragment(fragment);
    }

    public void replaceFragmentContainerWithFragment(Fragment fragment, String tag) {

//        clearFragmentStack();

        replaceFragmentToBackStack(fragment, tag);
    }

    public void popToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerViewId, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void replaceFragmentToBackStack(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerViewId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void addFragmentToBackStack(Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragmentContainerViewId, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    public void addFragmentToBackStack(int resourceId , Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resourceId, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    public void clearFragmentStack() {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

    public Fragment getTopFragment() {
        int index = fragmentManager.getBackStackEntryCount() - 1;
        fragmentManager.getFragments().size();
        FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(index);
        String tag = backEntry.getName();
        if (tag != null) {
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            return fragment;
        }
        return null;
    }

    public int getFragmentCount(){

        return fragmentManager.getBackStackEntryCount();
    }

    public void gotoBottomFragment() {


    }

}