package com.befresh.befreshapp.Navigationmain;

import android.app.Dialog;
import android.support.v4.app.Fragment;


public class MasterFragment extends Fragment {

	// returns the master activity
	public MasterActivity getMasterActivity() {
		MasterActivity masterActivity = (MasterActivity) getActivity();
		return masterActivity;
	}

	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
