package phalaenopsis.common.method.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CompositeIterator;

import phalaenopsis.common.entity.AppSettings;

public final class UUID64 {

	// private static final int TimestampBits = 32;
	private static final int MachineIDBits = 6;
	private static final int SequenceBits = 16;

	private static Date ZeroTime;
	private static byte MachineID;
	private static Object sync;

	private static volatile int lastTimestamp;

	@SuppressWarnings("unchecked")
	private static short lastSequence;

	public static UUID64 Empty;

	private long value;

	@Autowired
	private AppSettings config;

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UUID64.class);

	
	//private static java.util.Calendar calendar = java.util.Calendar.getInstance();
	
	// 1, 取得本地时间
	private static SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
	private static SimpleDateFormat dateFormatLocal;
	
	private static long zeroTime = Date.UTC(2017, 1, 1, 0, 0, 0) - Date.UTC(1970, 1, 1, 0, 0, 0);

	static {
		sync = new Object();

		//calendar.get(java.util.Calendar.ZONE_OFFSET);

		//ZeroTime = new Date(new Date().UTC(2017, 1, 1, 0, 0, 0));
		
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

		// AppSettings config = new AppSettings();
		// MachineID = Byte.parseByte();
		//
		// Empty = new UUID64(0, MachineID, (short) 0);
	}

	private UUID64() {
		// AppSettings config = new AppSettings();
		MachineID = Byte.parseByte(config.getMachineID());

		logger.info(config.getMachineID());

		Empty = new UUID64(0, MachineID, (short) 0);
	}

	public UUID64(int timestamp, byte machineID, short sequence) {
		value = (((long) timestamp) << MachineIDBits + SequenceBits)
				| (((long) (machineID & ((1 << MachineIDBits) - 1))) << SequenceBits) | (long) sequence;

		logger.debug("timestamp:" + timestamp + ","
									  + "MachineIDBits:" + MachineIDBits + ","
									  + "SequenceBits" + SequenceBits + "," 
									  + "machineID:" + machineID +","
									  + "sequence");
	}

	public long getValue() {
		return value;
	}

	public static UUID64 newUUID64() {
		int timestamp = getTimestamp();
		synchronized (sync) {
			if (timestamp == lastTimestamp) {
				lastSequence++;
				if (lastSequence == 0) {
					int time = 0;
					do {
						time++;
						if (time % 10 == 0)
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						else {
							Thread.yield();
						}
						timestamp = getTimestamp();
					} while (timestamp <= lastTimestamp);
				}
			} else {
				lastSequence = 0;
			}
			lastTimestamp = timestamp;
			return new UUID64(timestamp, MachineID, lastSequence);
		}
	}

	private static int getTimestamp() {
		try {
			return  (int)((dateFormatLocal.parse(dateFormatGmt.format(new Date())).getTime() - zeroTime)* 0.001);
			//return (int) ((int) (calendar.getTime().getTime() - ZeroTime.getTime()) * 0.001);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	public boolean Equals(UUID64 other) {
		return this.value == other.value;
	}

	public boolean Equals(Object obj) {
		if (obj instanceof UUID64)
			return this.equals((UUID64) obj);
		return false;
	}

	public int GetHashCode() {
		return ((int) ((value >> MachineIDBits) & 0x7fff0000)) | (int) (short) value;
	}
}
