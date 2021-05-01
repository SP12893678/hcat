package hcats;
import java.util.ArrayList;

public abstract class ApiBase {

	protected boolean ICD_10_CM_format = false;
	protected String API = "";
	protected int errorcode = -2;
	protected ArrayList<int[]> length;
	protected ArrayList<byte[]> buf;
    
	public ApiBase(String API) 
	{
		this.API = API;
		setBuffer();
		run();
		putErrorcode();
		dataStore();
	}
	 
	public void setBuffer()
	{
		length = new ArrayList<int[]>();
	    buf = new ArrayList<byte[]>();
		for(int i = 0 ;i < DataBase.getDatabase().data_totalbyte.get(API).size();i++)
        {
            int len = Integer.valueOf(DataBase.getDatabase().data_totalbyte.get(API).get(i));
            int[] tmp_length = {len};
            byte[] tmp_buf = new byte[tmp_length[0]];
            length.add(tmp_length);
            buf.add(tmp_buf);
        }
	}
	
	public void putErrorcode()
	{
		DataBase.getDatabase().list_errorcode.put(API, errorcode);
	}
	
	public void dataStore()
	{
		if(errorcode == 0)
        {
			DataBase.getDatabase().clear_value(API);
			if(!ICD_10_CM_format)
			{
				for(int i = 0;i < DataBase.getDatabase().data_totalbyte.get(API).size();i++)
				{
					if(DataBase.getDatabase().data_group_name.get(API).size()>0)
						DataBase.getDatabase().Cshis_storeBuf(API, buf.get(i),i);
					else
						DataBase.getDatabase().Cshis_storeBuf(API, buf.get(i),-1);
				}
			}
			else
				DataBase.getDatabase().Cshis_storeBuf_ICD(API,buf.get(0));
        }
	}
	
	public abstract void run();
}