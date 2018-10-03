base-utils



To setup the library:



1. In your root build.gradle

        allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

2. In your app build.gradle

        dependencies {
	        implementation 'com.github.hzaun:base-utils:v1.0.2'
	}

How to use::

activity_main.xml

<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".StartingPoint">

    <include
        android:id="@+id/toolbar"
        layout="@layout/hzaun_toolbar" />

    ...
</android.support.constraint.ConstraintLayout>

MainActivity.class

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_starting_point);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initApp() {
        super.initApp();

        context = this;

        uiComponents = new UIComponents(context, true);
        uiComponents.setToolbarItems(R.drawable.ic_menu, R.string.activity_main);

        baseUtils = new BaseUtils(context);
    }

    public void fetchData() {
        if (baseUtils.isOnline()) {
            baseUtils.getLoader();

            // call API
        }
    }

    public void parseData() {
        baseUtils.dismissLoader();

        ...
    }
}
