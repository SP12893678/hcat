package hcats;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DataBase {
    
    private static DataBase database = new DataBase();
    private JFrame frame; 
    public Map<Integer,String> errorcode_table = new HashMap<Integer,String>();
    public Map<String,Integer> list_errorcode = new HashMap<String,Integer>();
    public Map<String,String> data_api = new HashMap<String,String>();
    public Map<String,ArrayList<Integer>> data_totalbyte = new HashMap<String,ArrayList<Integer>>();
    public Map<String,ArrayList<String>> data_group_name = new HashMap<String,ArrayList<String>>();
    private Map<String,ArrayList<Integer>> data_group_count = new HashMap<String,ArrayList<Integer>>();
    private Map<String,ArrayList<Integer>> data_group_loopNum = new HashMap<String,ArrayList<Integer>>();
    public Map<String,ArrayList<String>> data_symbol = new HashMap<String,ArrayList<String>>();
    public Map<String,ArrayList<String>> data_value = new HashMap<String,ArrayList<String>>();
    public Map<String,ArrayList<Integer>> data_byte = new HashMap<String,ArrayList<Integer>>();
    private Map<String,ArrayList<Integer>> data_byte_gap = new HashMap<String,ArrayList<Integer>>();
    private ArrayList<Integer> data_list_totalbyte;
    public ArrayList<String> data_list_value;
    private ArrayList<String> data_list_symbol;
    private ArrayList<Integer> data_list_byte;
    private ArrayList<Integer> data_list_byte_gap;
    
    private DataBase() {
        try 
        {
            loadErrorCode();
            importfile();
        }
        catch (IOException ex) 
        {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DataBase getDatabase() 
    {
        return database;
    }
    
    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }
    
    private void loadErrorCode() throws IOException 
	{
		InputStream is = getClass().getResourceAsStream("/hcats/res/txt/ErrorCode.txt");
        InputStreamReader read = new InputStreamReader (is,"UTF-8"); 
        BufferedReader inputStream = new BufferedReader(read);
	    String data = null;
	    while((data = inputStream.readLine()) != null)
	    {
		    int errorcode = Integer.valueOf(data.substring(0, 4).trim());
		    String errormessage = data.substring(4);
		    errorcode_table.put(errorcode, errormessage);
	    } 
	}
    
    public boolean alert_error(int errorcode)
	{
	    if(errorcode != 0)
	    {
		    String result = "ErrorCode: " + errorcode + " (" + errorcode_table.get(errorcode) + ")";
		    JOptionPane.showMessageDialog(frame, result , "訊息",JOptionPane.INFORMATION_MESSAGE);
		    return false;
	    }
	    return true;
	}
    
    public String data_print_to_textarea(String API)
    {
		if(list_errorcode.get(API) == null || list_errorcode.get(API) == -2)
		{
			return "錯誤的API";
		}
		else
		{
			data_list_value = data_value.get(API);
	        data_list_symbol = data_symbol.get(API);
	        String data = API + " (" + data_api.get(API) + ")\n";
	        if(list_errorcode.get(API)!=0)
	        {
	        	data += "-------錯誤訊息-------\n";
	        	data += "ErrorCode: " + list_errorcode.get(API) + " (" + errorcode_table.get(list_errorcode.get(API)) + ")";
	        }
	        else
	        {
	        	data += "-------執行成功-------\n";
	        	if(data_group_name.get(API).size() == 0)
		        {
	                int count = data_list_symbol.size();
		            for(int i = 0 ;i < data_list_value.size();i++)
		            {
		            	if(data_list_symbol.size()> i)
		            		data += data_list_symbol.get(i%count) + ": ";
		                if(data_list_value.size() > i )
		                	data += data_list_value.get(i);
		                data += "\n";
		            }   
		        }
		        else
		        {
	                int tmp_k = 0;
	                int sym_pos = 0;
		            for(int i = 0 ;i < data_group_name.get(API).size();i++)
		            {
		                String group_name = data_group_name.get(API).get(i);
		                int loopNum = data_group_loopNum.get(API).get(i);
		                int count = data_group_count.get(API).get(i);
	                    data += group_name + "[共" + loopNum + "組" + " ─ 每組" + count + "筆]\n";
	                    data += "----------------------------\n";
		                for(int j = 0;j < loopNum;j++)
		                {
		                    data += group_name + "[第" + (j+1) + "組]\n";
		                    for(int k = 0;k < count;k++)
		                    {
		                        data += data_list_symbol.get(sym_pos + k%count) + ": ";
		                        if(data_list_value.size() > tmp_k + k )
		                        {
		                        	data += data_list_value.get(tmp_k + k);
		                        } 
		                        data += "\n";
	                        }
	                        tmp_k += count;
	                    }
		                if(data_list_symbol.get(0).equals(data_list_symbol.get(count)))
	                    	sym_pos += loopNum*count;
	                    else
	                    	sym_pos += count;
	                    data += "\n";	                 
		            }
		        }
	        }
	        return data;
		}
    }
    
    public void clear_value(String API)
    {
    	data_list_value = new ArrayList<String>();
    	data_value.put(API, data_list_value);
    }
    
    public void Cshis_storeBuf(String API, byte[] buf ,int group)
    {
        data_list_byte = data_byte.get(API);
        data_list_value = data_value.get(API);
        int index = 0;
        int len;
        int pos = 0;
        int times = 1;
        if(data_totalbyte.get(API).size()==1)
        {
        	times = (data_group_name.get(API).size() >= 1)?data_group_name.get(API).size():0;
        }
        else
        {
        	times = group + 1;
        }
        while(group<times)
        {
        	int count = (group>-1)?data_group_count.get(API).get(group):data_list_byte.size();
            int loop = (group>-1)?data_group_loopNum.get(API).get(group):1;
            for(int i = 0 ;i < group; i++)
            {
                pos += data_group_count.get(API).get(i);
            }
            for(int i = 0 ;i < loop*count; i++)
            {
                len = data_list_byte.get(pos +  i%count);
                data_list_value.add(new String(buf, index, len).trim());
                index += len;
            }
            group++;
        }
        data_value.put(API, data_list_value);
    }

    public void Cshis_storeBuf_ICD(String API, byte[] buf)
    {
        data_list_byte_gap = data_byte_gap.get(API);
        data_list_byte = data_byte.get(API);
        data_list_value = data_value.get(API);
        int index = 0;
        int len;
        for(int i = 0 ;i < data_list_byte.size(); i++)
        {
            index += data_list_byte_gap.get(i);
            len = data_list_byte.get(i);
            data_list_value.add(new String(buf, index, len).trim());
            index += len;
         }
        data_value.put(API, data_list_value);
    }
    
    public ArrayList<String> getList(String str)
    {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer strt = new StringTokenizer(str, " []\t\n\r");
        while (strt.hasMoreTokens())
        {
        	list.add(strt.nextToken());
        }
        return list;
    }
    
    private void importfile() throws IOException
    {
    	InputStream is = getClass().getResourceAsStream("/hcats/res/txt/test.txt");
        InputStreamReader read = new InputStreamReader (is,"UTF-8"); 
        BufferedReader inputStream = new BufferedReader(read);
        String data = null;
        String API = null;
        String API_ch = null;
        int Byte;
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> count = new ArrayList<Integer>();
        ArrayList<Integer> loopNum = new ArrayList<Integer>();
        while((data = inputStream.readLine()) != null)
        {
            ArrayList<String> list = getList(data);
            if(list.get(0).equals("#T"))
            {
                API = list.get(1);
                API_ch = list.get(2);
                name = new ArrayList<String>();
                count = new ArrayList<Integer>();
                loopNum = new ArrayList<Integer>();
                data_list_totalbyte = new ArrayList<Integer>();
                for(int i = 3 ;i < list.size();i++)
                {
                    Byte = Integer.valueOf(list.get(i));
                    data_list_totalbyte.add(Byte);
                }
                data_list_value = new ArrayList<String>();
                data_list_symbol = new ArrayList<String>();
                data_list_byte = new ArrayList<Integer>();
                data_list_byte_gap = new ArrayList<Integer>();
            }
            else if(list.get(0).equals("#V"))
            {
                data_list_symbol.add(list.get(1));
                data_list_byte.add(Integer.valueOf(list.get(2)));
                if(list.size()>3)
                    data_list_byte_gap.add(Integer.valueOf(list.get(3)));
            }
            else if(list.get(0).equals("#L"))
            {
                count.add(Integer.valueOf(list.get(1)));
                loopNum.add(Integer.valueOf(list.get(2)));
                name.add(list.get(3));
            }
            else if(list.get(0).equals("#E"))
            {
                data_api.put(API, API_ch);
                data_group_name.put(API, name);
                data_group_count.put(API, count);
                data_group_loopNum.put(API, loopNum);
                data_totalbyte.put(API, data_list_totalbyte);
                data_symbol.put(API, data_list_symbol);
                data_value.put(API, data_list_value);
                data_byte.put(API, data_list_byte);
                if(data_list_byte_gap.size() > 0)
                    data_byte_gap.put(API, data_list_byte_gap);
            }
        }     
    }
}


