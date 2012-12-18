package com.questy.admin.scrapper;


import java.io.IOException;
import java.util.*;

public class MITPeopleScrapper {

    public static Map<String, Integer> proxies = new HashMap<String, Integer>();
    public static List<String> content = Collections.synchronizedList(new ArrayList<String>());

    static {
        proxies.put("186.38.23.180     ".trim(), 80    );
        proxies.put("186.38.23.180     ".trim(), 3128  );
        proxies.put("190.211.132.26    ".trim(), 8080  );
        proxies.put("190.211.132.65    ".trim(), 8080  );
        proxies.put("200.61.31.72      ".trim(), 8080  );
        proxies.put("200.114.103.89    ".trim(), 8080  );
        proxies.put("190.211.131.4     ".trim(), 8080  );
        proxies.put("201.251.62.137    ".trim(), 8080  );
        proxies.put("186.129.250.136   ".trim(), 8080  );
        proxies.put("186.148.149.217   ".trim(), 8080  );
        proxies.put("200.114.103.221   ".trim(), 8080  );
        proxies.put("186.153.120.42    ".trim(), 8080  );
        proxies.put("200.114.103.213   ".trim(), 8080  );
        proxies.put("186.38.35.74      ".trim(), 8080  );
        proxies.put("190.211.132.1     ".trim(), 8080  );
        proxies.put("190.211.132.29    ".trim(), 8080  );
        proxies.put("190.108.43.180    ".trim(), 8080  );
        proxies.put("200.5.113.202     ".trim(), 8080  );
        proxies.put("200.114.103.33    ".trim(), 8080  );
        proxies.put("170.210.123.137   ".trim(), 3128  );
        proxies.put("190.211.132.33    ".trim(), 8080  );
        proxies.put("168.226.35.19     ".trim(), 8080  );
        proxies.put("211.154.83.39     ".trim(), 82    );
        proxies.put("59.37.168.16      ".trim(), 8081  );
        proxies.put("211.167.112.17    ".trim(), 82    );
        proxies.put("211.167.112.15    ".trim(), 82    );
        proxies.put("211.167.112.14    ".trim(), 80    );
        proxies.put("211.167.112.18    ".trim(), 80    );
        proxies.put("58.67.147.196     ".trim(), 8080  );
        proxies.put("211.167.112.18    ".trim(), 82    );
        proxies.put("211.154.83.39     ".trim(), 80    );
        proxies.put("211.154.83.35     ".trim(), 82    );
        proxies.put("60.28.250.194     ".trim(), 82    );
        proxies.put("211.154.83.37     ".trim(), 82    );
        proxies.put("211.167.112.15    ".trim(), 80    );
        proxies.put("123.129.214.155   ".trim(), 80    );
        proxies.put("219.243.220.100   ".trim(), 8080  );
        proxies.put("211.167.112.14    ".trim(), 82    );
        proxies.put("219.243.220.13    ".trim(), 8080  );
        proxies.put("211.167.112.17    ".trim(), 80    );
        proxies.put("221.6.15.157      ".trim(), 80    );
        proxies.put("221.6.15.156      ".trim(), 82    );
        proxies.put("221.6.15.157      ".trim(), 82    );
        proxies.put("122.96.59.103     ".trim(), 80    );
        proxies.put("122.96.59.103     ".trim(), 81    );
        proxies.put("218.25.59.1       ".trim(), 80    );
        proxies.put("58.67.147.200     ".trim(), 8080  );
        proxies.put("122.96.59.103     ".trim(), 83    );
        proxies.put("211.138.123.60    ".trim(), 80    );
        proxies.put("211.136.10.24     ".trim(), 80    );
        proxies.put("122.96.59.103     ".trim(), 82    );
        proxies.put("218.92.252.37     ".trim(), 8080  );
        proxies.put("58.67.147.201     ".trim(), 8080  );
        proxies.put("124.240.187.80    ".trim(), 84    );
        proxies.put("122.90.14.85      ".trim(), 80    );
        proxies.put("122.72.28.21      ".trim(), 80    );
        proxies.put("122.72.28.22      ".trim(), 80    );
        proxies.put("60.191.232.230    ".trim(), 80    );
        proxies.put("61.55.141.10      ".trim(), 80    );
        proxies.put("219.231.140.3     ".trim(), 8909  );
        proxies.put("219.224.27.141    ".trim(), 808   );
        proxies.put("202.75.217.156    ".trim(), 80    );
        proxies.put("211.136.10.25     ".trim(), 80    );
        proxies.put("60.191.124.58     ".trim(), 3128  );
        proxies.put("166.111.13.75     ".trim(), 8909  );
        proxies.put("218.25.249.186    ".trim(), 80    );
        proxies.put("123.150.159.246   ".trim(), 80    );
        proxies.put("221.6.15.156      ".trim(), 80    );
        proxies.put("211.154.83.38     ".trim(), 82    );
        proxies.put("123.150.159.244   ".trim(), 80    );
        proxies.put("125.37.63.72      ".trim(), 8909  );
        proxies.put("124.240.187.80    ".trim(), 83    );
        proxies.put("221.6.15.158      ".trim(), 82    );
        proxies.put("211.136.86.204    ".trim(), 8085  );
        proxies.put("218.94.149.114    ".trim(), 8080  );
        proxies.put("211.136.10.29     ".trim(), 80    );
        proxies.put("125.39.66.132     ".trim(), 80    );
        proxies.put("115.25.216.6      ".trim(), 80    );
        proxies.put("113.230.76.234    ".trim(), 80    );
        proxies.put("211.136.86.200    ".trim(), 8085  );
        proxies.put("202.38.95.66      ".trim(), 8080  );
        proxies.put("118.186.13.179    ".trim(), 80    );
        proxies.put("223.202.36.166    ".trim(), 80    );
        proxies.put("202.121.129.204   ".trim(), 8080  );
        proxies.put("121.251.255.237   ".trim(), 8080  );
        proxies.put("124.240.187.80    ".trim(), 85    );
        proxies.put("123.139.155.104   ".trim(), 80    );
        proxies.put("123.139.155.107   ".trim(), 80    );
        proxies.put("122.72.20.127     ".trim(), 80    );
        proxies.put("122.141.243.215   ".trim(), 80    );
        proxies.put("58.67.147.203     ".trim(), 8080  );
        proxies.put("218.15.164.131    ".trim(), 8080  );
        proxies.put("219.159.188.30    ".trim(), 8909  );
        proxies.put("202.112.112.249   ".trim(), 8080  );
        proxies.put("113.18.101.243    ".trim(), 8080  );
        proxies.put("211.154.83.35     ".trim(), 80    );
        proxies.put("211.154.83.37     ".trim(), 80    );
        proxies.put("210.31.64.13      ".trim(), 8909  );
        proxies.put("119.4.250.97      ".trim(), 80    );
        proxies.put("118.123.12.65     ".trim(), 8080  );
        proxies.put("114.255.193.231   ".trim(), 8080  );
        proxies.put("211.136.10.30     ".trim(), 80    );
        proxies.put("211.140.189.247   ".trim(), 80    );
        proxies.put("122.225.22.22     ".trim(), 8080  );
        proxies.put("110.53.48.54      ".trim(), 8080  );
        proxies.put("124.240.187.80    ".trim(), 81    );
        proxies.put("211.154.83.38     ".trim(), 80    );
        proxies.put("221.2.226.12      ".trim(), 8080  );
        proxies.put("122.72.80.106     ".trim(), 80    );
        proxies.put("221.195.42.195    ".trim(), 8080  );
        proxies.put("123.139.155.103   ".trim(), 82    );
        proxies.put("221.13.79.20      ".trim(), 80    );
        proxies.put("123.150.159.242   ".trim(), 80    );
        proxies.put("122.72.28.10      ".trim(), 80    );
        proxies.put("202.118.250.234   ".trim(), 8080  );
        proxies.put("115.236.38.46     ".trim(), 8909  );
        proxies.put("119.148.160.2     ".trim(), 80    );
        proxies.put("202.105.139.93    ".trim(), 8088  );
        proxies.put("218.192.55.54     ".trim(), 8909  );
        proxies.put("58.67.147.197     ".trim(), 8080  );
        proxies.put("122.72.80.107     ".trim(), 80    );
        proxies.put("222.141.199.150   ".trim(), 80    );
        proxies.put("61.55.141.12      ".trim(), 80    );
        proxies.put("125.211.198.74    ".trim(), 80    );
        proxies.put("123.139.155.103   ".trim(), 80    );
        proxies.put("123.139.155.106   ".trim(), 80    );
        proxies.put("61.181.22.156     ".trim(), 80    );
        proxies.put("122.72.102.62     ".trim(), 80    );
        proxies.put("219.159.198.57    ".trim(), 8080  );
        proxies.put("122.72.80.108     ".trim(), 80    );
        proxies.put("222.83.14.142     ".trim(), 3128  );
        proxies.put("118.102.27.216    ".trim(), 8080  );
        proxies.put("122.72.112.148    ".trim(), 80    );
        proxies.put("122.72.0.113      ".trim(), 80    );
        proxies.put("122.141.242.135   ".trim(), 80    );
        proxies.put("180.186.36.71     ".trim(), 8080  );
        proxies.put("211.136.10.25     ".trim(), 80    );
        proxies.put("211.161.152.106   ".trim(), 80    );
        proxies.put("211.161.152.98    ".trim(), 80    );
        proxies.put("211.142.236.133   ".trim(), 8080  );
        proxies.put("218.203.121.84    ".trim(), 8080  );
        proxies.put("211.142.236.137   ".trim(), 8080  );
        proxies.put("122.72.28.24      ".trim(), 80    );
        proxies.put("125.39.78.253     ".trim(), 8086  );
        proxies.put("122.72.28.12      ".trim(), 80    );
        proxies.put("61.155.156.159    ".trim(), 80    );
        proxies.put("122.72.28.14      ".trim(), 80    );
        proxies.put("222.73.205.2      ".trim(), 80    );
        proxies.put("122.72.20.124     ".trim(), 80    );
        proxies.put("211.162.121.182   ".trim(), 80    );
        proxies.put("202.55.5.245      ".trim(), 8081  );
        proxies.put("211.142.236.133   ".trim(), 80    );
        proxies.put("183.60.97.98      ".trim(), 80    );
        proxies.put("60.219.8.4        ".trim(), 80    );
        proxies.put("223.5.16.143      ".trim(), 80    );
        proxies.put("183.60.97.99      ".trim(), 80    );
        proxies.put("221.131.113.115   ".trim(), 80    );
        proxies.put("211.144.76.7      ".trim(), 8181  );
        proxies.put("211.142.236.131   ".trim(), 80    );
        proxies.put("211.142.236.137   ".trim(), 80    );
        proxies.put("125.39.66.130     ".trim(), 80    );
        proxies.put("116.28.65.249     ".trim(), 80    );
        proxies.put("221.238.219.212   ".trim(), 80    );
        proxies.put("122.72.33.138     ".trim(), 80    );
        proxies.put("122.72.124.42     ".trim(), 80    );
        proxies.put("123.150.159.245   ".trim(), 80    );
        proxies.put("122.72.33.139     ".trim(), 80    );
        proxies.put("58.251.57.78      ".trim(), 8001  );
        proxies.put("61.191.25.213     ".trim(), 80    );
        proxies.put("125.216.144.199   ".trim(), 8080  );
        proxies.put("122.90.14.226     ".trim(), 80    );
        proxies.put("125.39.68.130     ".trim(), 80    );
        proxies.put("186.228.41.162    ".trim(), 3128  );
        proxies.put("187.6.252.146     ".trim(), 3128  );
        proxies.put("200.182.190.149   ".trim(), 8080  );
        proxies.put("200.153.150.142   ".trim(), 3128  );
        proxies.put("187.111.222.222   ".trim(), 8080  );
        proxies.put("189.84.226.85     ".trim(), 8080  );
        proxies.put("200.182.190.147   ".trim(), 8080  );
        proxies.put("187.16.63.93      ".trim(), 8080  );
        proxies.put("200.178.100.97    ".trim(), 8080  );
        proxies.put("200.208.251.214   ".trim(), 8080  );
        proxies.put("189.84.226.82     ".trim(), 8080  );
        proxies.put("177.36.242.97     ".trim(), 8080  );
        proxies.put("187.16.250.34     ".trim(), 80    );
        proxies.put("187.87.155.2      ".trim(), 8080  );
        proxies.put("189.22.138.171    ".trim(), 8080  );
        proxies.put("187.115.52.40     ".trim(), 3128  );
        proxies.put("200.208.251.215   ".trim(), 8080  );
        proxies.put("200.181.109.20    ".trim(), 80    );
        proxies.put("200.182.190.151   ".trim(), 8080  );
        proxies.put("200.201.188.158   ".trim(), 80    );
        proxies.put("200.206.14.26     ".trim(), 3128  );
        proxies.put("187.54.67.169     ".trim(), 8081  );
        proxies.put("177.36.242.69     ".trim(), 8080  );
        proxies.put("189.115.161.201   ".trim(), 3128  );
        proxies.put("200.253.116.5     ".trim(), 3128  );
        proxies.put("200.253.116.2     ".trim(), 3128  );
        proxies.put("200.146.85.134    ".trim(), 3128  );
        proxies.put("177.43.72.250     ".trim(), 80    );
        proxies.put("189.108.118.194   ".trim(), 3128  );
        proxies.put("200.155.37.241    ".trim(), 3128  );
        proxies.put("186.215.207.141   ".trim(), 3128  );
        proxies.put("186.233.112.2     ".trim(), 3128  );
        proxies.put("177.69.11.1       ".trim(), 8080  );
        proxies.put("189.45.245.34     ".trim(), 8080  );
        proxies.put("200.196.51.130    ".trim(), 8080  );
        proxies.put("201.12.116.18     ".trim(), 80    );
        proxies.put("186.192.17.138    ".trim(), 8080  );
        proxies.put("177.23.151.146    ".trim(), 8080  );
        proxies.put("186.215.202.163   ".trim(), 8080  );
        proxies.put("187.111.223.10    ".trim(), 8080  );
        proxies.put("201.65.237.68     ".trim(), 3128  );
        proxies.put("146.164.42.140    ".trim(), 80    );
        proxies.put("186.215.70.74     ".trim(), 80    );
        proxies.put("177.100.19.151    ".trim(), 8080  );
        proxies.put("187.72.145.54     ".trim(), 8080  );
        proxies.put("200.182.190.154   ".trim(), 8080  );
        proxies.put("200.195.176.77    ".trim(), 8080  );
        proxies.put("187.49.7.99       ".trim(), 8080  );
        proxies.put("200.168.128.234   ".trim(), 8080  );
        proxies.put("187.120.209.43    ".trim(), 8080  );
        proxies.put("54.247.10.138     ".trim(), 80    );
        proxies.put("23.20.161.66      ".trim(), 80    );
        proxies.put("74.86.2.131       ".trim(), 8080  );
        proxies.put("98.211.165.194    ".trim(), 8085  );
        proxies.put("173.224.210.119   ".trim(), 3128  );
        proxies.put("23.21.140.219     ".trim(), 3128  );
        proxies.put("65.51.123.181     ".trim(), 80    );
        proxies.put("23.22.226.194     ".trim(), 80    );
        proxies.put("69.36.162.212     ".trim(), 3128  );
        proxies.put("108.46.251.186    ".trim(), 3128  );
        proxies.put("216.244.71.92     ".trim(), 8087  );
        proxies.put("69.28.57.35       ".trim(), 9999  );
        proxies.put("71.189.47.2       ".trim(), 8081  );
        proxies.put("68.71.76.242      ".trim(), 8082  );
        proxies.put("67.44.130.47      ".trim(), 87    );
        proxies.put("128.173.88.201    ".trim(), 3128  );
        proxies.put("67.55.121.217     ".trim(), 8118  );
        proxies.put("67.55.121.215     ".trim(), 8118  );
        proxies.put("173.48.37.68      ".trim(), 8001  );
        proxies.put("174.129.241.205   ".trim(), 80    );
        proxies.put("208.15.40.99      ".trim(), 80    );
        proxies.put("216.244.86.163    ".trim(), 80    );
        proxies.put("173.48.37.68      ".trim(), 80    );
        proxies.put("69.29.105.153     ".trim(), 8080  );
        proxies.put("173.48.37.68      ".trim(), 8002  );
        proxies.put("119.254.245.126   ".trim(), 80    );
        proxies.put("50.7.10.34        ".trim(), 8080  );
        proxies.put("184.73.248.163    ".trim(), 80    );
        proxies.put("58.249.116.202    ".trim(), 80    );
        proxies.put("23.23.174.247     ".trim(), 80    );
        proxies.put("178.18.17.208     ".trim(), 8080  );
        proxies.put("184.174.172.93    ".trim(), 80    );
        proxies.put("109.123.126.253   ".trim(), 8080  );
        proxies.put("195.62.49.82      ".trim(), 80    );
        proxies.put("78.30.229.65      ".trim(), 808   );
        proxies.put("82.199.105.217    ".trim(), 3128  );
        proxies.put("92.44.114.216     ".trim(), 8088  );
        proxies.put("95.80.92.52       ".trim(), 3128  );
        proxies.put("110.34.179.132    ".trim(), 8087  );
        proxies.put("88.247.110.221    ".trim(), 8088  );
        proxies.put("80.192.41.229     ".trim(), 3128  );
        proxies.put("176.31.165.26     ".trim(), 3128  );
        proxies.put("124.125.50.81     ".trim(), 6588  );
        proxies.put("115.248.145.12    ".trim(), 8080  );
        proxies.put("79.136.184.117    ".trim(), 8080  );
        proxies.put("77.38.171.87      ".trim(), 8080  );
        proxies.put("80.247.100.250    ".trim(), 3128  );
        proxies.put("89.251.103.130    ".trim(), 8080  );
        proxies.put("82.207.43.156     ".trim(), 3128  );
        proxies.put("122.155.0.201     ".trim(), 3128  );
        proxies.put("176.196.170.137   ".trim(), 808   );
        proxies.put("188.93.20.179     ".trim(), 8080  );
        proxies.put("195.25.110.87     ".trim(), 3128  );
        proxies.put("81.163.1.34       ".trim(), 8080  );
        proxies.put("192.162.150.77    ".trim(), 8080  );
        proxies.put("118.175.4.82      ".trim(), 8080  );
        proxies.put("61.19.30.123      ".trim(), 3128  );
        proxies.put("176.31.107.35     ".trim(), 3128  );
        proxies.put("195.64.211.173    ".trim(), 3128  );
        proxies.put("82.207.47.7       ".trim(), 3129  );
        proxies.put("219.94.243.100    ".trim(), 8080  );
        proxies.put("180.183.70.254    ".trim(), 8080  );
        proxies.put("91.121.100.200    ".trim(), 8118  );
        proxies.put("49.212.81.128     ".trim(), 8888  );
        proxies.put("93.78.125.33      ".trim(), 8008  );
        proxies.put("60.249.149.29     ".trim(), 8080  );
        proxies.put("109.74.239.42     ".trim(), 8080  );
        proxies.put("115.248.186.18    ".trim(), 3128  );
        proxies.put("61.7.180.140      ".trim(), 3128  );
        proxies.put("202.88.251.138    ".trim(), 7164  );
        proxies.put("119.235.53.237    ".trim(), 8080  );
        proxies.put("122.146.176.41    ".trim(), 8080  );
        proxies.put("93.186.122.114    ".trim(), 8080  );
        proxies.put("78.186.180.73     ".trim(), 8086  );
        proxies.put("164.77.196.78     ".trim(), 80    );
        proxies.put("122.170.3.229     ".trim(), 3128  );
        proxies.put("190.82.89.154     ".trim(), 3128  );
        proxies.put("190.82.89.156     ".trim(), 3128  );
        proxies.put("203.202.243.112   ".trim(), 8080  );
        proxies.put("87.98.136.60      ".trim(), 80    );
        proxies.put("188.165.249.205   ".trim(), 3128  );
        proxies.put("89.234.195.145    ".trim(), 8000  );
        proxies.put("95.215.48.146     ".trim(), 8080  );
        proxies.put("88.156.71.247     ".trim(), 8080  );
        proxies.put("176.36.151.85     ".trim(), 80    );
        proxies.put("77.223.243.122    ".trim(), 8080  );
        proxies.put("178.218.232.252   ".trim(), 8080  );
        proxies.put("81.214.15.106     ".trim(), 8080  );
        proxies.put("81.213.157.71     ".trim(), 8080  );
        proxies.put("180.211.112.218   ".trim(), 8080  );
        proxies.put("78.188.3.173      ".trim(), 80    );
        proxies.put("81.31.161.200     ".trim(), 8080  );
        proxies.put("81.213.157.71     ".trim(), 80    );
        proxies.put("190.116.87.4      ".trim(), 8080  );
        proxies.put("183.89.91.180     ".trim(), 3128  );
        proxies.put("119.160.192.122   ".trim(), 3128  );
        proxies.put("85.185.157.15     ".trim(), 8080  );
        proxies.put("175.136.234.10    ".trim(), 3128  );
        proxies.put("200.27.114.228    ".trim(), 8080  );
        proxies.put("80.233.133.115    ".trim(), 8080  );
        proxies.put("190.96.64.234     ".trim(), 8080  );
        proxies.put("217.219.175.71    ".trim(), 8080  );
        proxies.put("27.120.86.150     ".trim(), 3128  );
        proxies.put("78.188.47.21      ".trim(), 8080  );
        proxies.put("113.21.228.214    ".trim(), 8080  );
        proxies.put("217.11.19.130     ".trim(), 80    );
        proxies.put("27.120.81.191     ".trim(), 3128  );
        proxies.put("120.50.0.60       ".trim(), 8080  );
        proxies.put("175.136.246.105   ".trim(), 8080  );
        proxies.put("110.171.148.197   ".trim(), 3128  );
        proxies.put("190.196.19.107    ".trim(), 3128  );
        proxies.put("203.170.192.244   ".trim(), 3128  );
        proxies.put("94.183.125.246    ".trim(), 8080  );
        proxies.put("217.171.58.106    ".trim(), 8080  );
        proxies.put("180.211.180.194   ".trim(), 8080  );
        proxies.put("61.7.252.67       ".trim(), 3128  );
        proxies.put("49.212.149.114    ".trim(), 8090  );
        proxies.put("203.202.240.114   ".trim(), 8080  );
        proxies.put("217.26.14.18      ".trim(), 3128  );
        proxies.put("190.234.11.1      ".trim(), 3128  );
        proxies.put("178.79.148.187    ".trim(), 80    );
        proxies.put("171.101.216.18    ".trim(), 3128  );
        proxies.put("77.122.91.226     ".trim(), 8080  );
        proxies.put("113.53.240.90     ".trim(), 3128  );
        proxies.put("46.164.138.135    ".trim(), 8080  );
        proxies.put("59.90.174.96      ".trim(), 3128  );
        proxies.put("91.98.44.218      ".trim(), 8080  );
        proxies.put("46.105.20.6       ".trim(), 8080  );
        proxies.put("117.239.113.34    ".trim(), 3128  );
        proxies.put("202.151.248.19    ".trim(), 8080  );
        proxies.put("93.157.103.243    ".trim(), 80    );
        proxies.put("117.58.241.139    ".trim(), 80    );
        proxies.put("80.58.250.68      ".trim(), 80    );
        proxies.put("37.72.10.252      ".trim(), 8080  );
        proxies.put("85.152.17.140     ".trim(), 80    );
        proxies.put("193.147.86.249    ".trim(), 3128  );
        proxies.put("203.162.175.86    ".trim(), 80    );
        proxies.put("89.28.10.166      ".trim(), 8080  );
        proxies.put("188.142.91.208    ".trim(), 80    );
        proxies.put("81.36.189.102     ".trim(), 8080  );
        proxies.put("123.200.155.65    ".trim(), 8081  );
        proxies.put("79.170.50.25      ".trim(), 80    );
        proxies.put("87.255.69.239     ".trim(), 80    );
        proxies.put("92.39.54.161      ".trim(), 80    );
        proxies.put("87.101.130.218    ".trim(), 8080  );
        proxies.put("41.186.3.170      ".trim(), 80    );
        proxies.put("41.197.132.186    ".trim(), 80    );
        proxies.put("113.160.244.109   ".trim(), 8080  );
        proxies.put("150.214.20.231    ".trim(), 9797  );
        proxies.put("84.20.82.82       ".trim(), 8080  );
        proxies.put("178.168.88.82     ".trim(), 8088  );
        proxies.put("82.223.130.77     ".trim(), 80    );
        proxies.put("41.186.3.170      ".trim(), 8000  );



    }


    public static void main (String[] args) throws IOException, InterruptedException {

        // Generating three letter combinations
        List<String> tooMany = new ArrayList<String>();




      tooMany.add("ALEXANDEF");
      tooMany.add("ANDERSONN");
      tooMany.add("ANDREWQ");
      tooMany.add("CAMPBELLB");
      tooMany.add("CAMPBEU");
      tooMany.add("CARTERI");
      tooMany.add("CHEUNGF");
      tooMany.add("CHRISTW");
      tooMany.add("COLLINSG");
      tooMany.add("FERNANDEK");
      tooMany.add("JOHNSONG");
      tooMany.add("ROBERTSZ");
      tooMany.add("STEPHENJ");
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
     
        
        
        





        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (String too : tooMany) {

//            for (int a = 0; a < letters.length(); a++) {

//                content.add(too + letters.charAt(a));
                content.add(too);

//            }

        }

        // Clearing the three letters to where we left off...
//        String lastContent = "DANIELZ";
//        int lastIndex = content.indexOf(lastContent);
//        content = content.subList(lastIndex, content.size());

        System.out.println(content);

        // Looping over proxies to generate threads
        for (String proxyHost : proxies.keySet()) {

            int proxyPort = proxies.get(proxyHost);

            // Creating scrapper
            MITPeopleScrapperThread.spawn(proxyHost, proxyPort, 0);

        }

        System.out.println("");

    }

}
