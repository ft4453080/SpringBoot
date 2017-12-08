package com.highrock.web;

import com.highrock.utils.LogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;

/**
 *  返回日志的最后N行数据
 */
@Controller
public class LogController {
    @Autowired
    private LogProperties logProperties;
    private final static int logNumber = 80 ;   //默认打印80行日志数据
  /*  @Value("${log.file}")
    private String logFile;*/

    @RequestMapping("/log")
    public String logLastHH(Model model) {
        String log = read(logProperties.getPath(),logNumber);
        model.addAttribute("log", log);
        return "log";
    }

    @RequestMapping("/log/{number}")
    public String logLastCustom(@PathVariable Integer number ,Model model) {
        String log = read(logProperties.getPath(),number);
        model.addAttribute("log", log);
        return "log";
    }

    public String read(String filename, int number) {
        RandomAccessFile rf = null;
        StringBuilder sb = new StringBuilder();
        try {
            rf = new RandomAccessFile(filename, "r");
            long len = rf.length();
            long end = len - 1;
            int lineNumber = 0 ;
            int c ;
            while(lineNumber< number+1 ){
                rf.seek(end);
                c = rf.read();
                if (c == '\n') {
                    end--;
                    lineNumber++;
                }else{
                    end--;
                }
                if(end<=0){
                    rf.seek(0);  //end=0,代表文件到头了,那么全文件输出,定位到0
                    lineNumber++;
                    break;
                }
            }
            if(end!=0){     //如果不是从头开始,那么要空出/r/n的两个位置
                end+=2 ;
                rf.seek(end);
            }

            for (int i = 0; i < lineNumber-1 ; i++) {  //用number,数值超出了,打印一堆null
                sb.append(rf.readLine()+"<br/>");      //thymeleaf模板<span th:utext="${log}"></span>标签可以用<Br/>换行
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null)
                    rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //日志策略2:列出所有天数的日志
    @RequestMapping("/logs")
    public String logList(Model model) {
        System.out.println(logProperties.getPath());
        File file = new File(logProperties.getPath());
        if(!file.isDirectory()){
            throw new RuntimeException("日志文件路径有误!请查正!");
        }
        String[] fileNames = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.contains(".log") && name.contains("logger.")){
                    return true;
                }
                return false;
            }
        });
        model.addAttribute("arrays", fileNames);
        return "logs";
    }

    @RequestMapping("/logs/days/{fileName}")
    public String logDays(@PathVariable String fileName,Model model) {
        StringBuilder sb = null ;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(logProperties.getPath()+"/"+fileName+".log"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String str;
            sb = new StringBuilder();
            while((str=br.readLine())!=null) {
                sb.append(str+"<br/>");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        model.addAttribute("log", sb.toString());
        return "log";
    }
}