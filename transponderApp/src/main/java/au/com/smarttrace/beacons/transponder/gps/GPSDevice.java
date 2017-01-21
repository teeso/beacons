package au.com.smarttrace.beacons.transponder.gps;

import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;

import au.com.smarttrace.beacons.Device;
import au.com.smarttrace.beacons.DeviceEvent;
import au.com.smarttrace.beacons.DeviceManager;
import au.com.smarttrace.beacons.InternalDevice;
import au.com.smarttrace.beacons.NoSuchDeviceException;
import au.com.smarttrace.beacons.tracker.LocationCompacter;
import au.com.smarttrace.beacons.tracker.Tracking;
import au.com.smarttrace.beacons.transponder.R;

/**
 *
 */
public class GPSDevice extends InternalDevice implements LocationListener {

    public static final String IDENTIFIER = "01_internal_GPS";
    public static final String KEY_LOCATION = "location";

    private int signal;

    private Location location;

    public GPSDevice() {
    }

    @Override
    public Device init(Context context, ScanResult deviceResult) {
        super.init(context, deviceResult);
        name = context.getResources().getString(R.string.title_gps);
        signal = -100;
        return this;
    }

    @Override
    public int getTitle() {
        return R.string.title_gps;
    }

    @Override
    public int getSignal() {
        return signal;
    }

    @Override
    public String getId() {
        return IDENTIFIER;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location!=null) {
            this.location = location;
            signal = - 100 + (int) (100/Math.min(location.getAccuracy(), 1d));
            new Handler().post(new Runnable() {@Override public void run() {Toast.makeText(context, "Location: " + String.valueOf(signal), Toast.LENGTH_SHORT).show();}});
            fireUpdate(String.valueOf(location));
            addSample(KEY_LOCATION, location);
        }
    }

    @Override
    public synchronized void connect() {
        //XXX: override bluetooth behaviour -> TODO: abstract Device hierarchy
    }

    @Override
    public synchronized void disconnect() {
        //XXX: override bluetooth behaviour -> TODO: abstract Device hierarchy
    }

    @Override
    public int getConnectionState() {
        return signal<0 ? BluetoothProfile.STATE_CONNECTED : BluetoothProfile.STATE_DISCONNECTED;//XXX: fake -> TODO: abstract Device hierarchy
    }

    @Override
    protected int getRowContentLayout() {
        return R.layout.row_gps_content;
    }

    @Override
    protected void populateRowContent(View content) {
        TextView tv = (TextView) content.findViewById(R.id.gps_latitude);
        tv.setText(R.string.latitude);
        tv.append(": ");
        tv.append(String.format("%3.4f", location!=null ? location.getLatitude() : " - "));
        tv = (TextView) content.findViewById(R.id.gps_longitude);
        tv.setText(R.string.longitude);
        tv.append(": ");
        tv.append(String.format("%3.4f", location!=null ? location.getLongitude() : " - "));
    }

    @Override
    public void onLocationChanged(final Location location) {
        setLocation(location);
    }
}
