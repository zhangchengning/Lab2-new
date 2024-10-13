import java.util.*;  
  
class Solution14 {  
    static final int SEG_COUNT = 4;  
    List<String> ans = new ArrayList<String>();  
    int[] segments = new int[SEG_COUNT];  // 修正数组初始化语法错误  
  
    public List<String> restoreIpAddresses(String s) {  
        dfs(s, 0, 0);  
        return ans;  
    }  
  
    public void dfs(String s, int segId, int segStart) {  
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案  
        if (segId == SEG_COUNT) {  // 修正等号使用错误  
            if (segStart == s.length()) {  
                StringBuffer ipAddr = new StringBuffer();  
                for (int i = 0; i < SEG_COUNT; ++i) {  
                    ipAddr.append(segments[i]);  
                    if (i != SEG_COUNT - 1) {  
                        ipAddr.append('.');  
                    }  
                }  
                ans.add(ipAddr.toString());  
            }  
            return;  
        }  
  
        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯  
        if (segStart == s.length()) {  
            return;  
        }  
  
        // 由于不能有前导零，如果当前段起始字符为 0，那么这一段 IP 地址只能为 0，并且长度为 1  
        if (s.charAt(segStart) == '0') {  
            segments[segId] = 0;  
            dfs(s, segId + 1, segStart + 1);  
        } else {  
            // 一般情况，枚举每一种可能性并递归  
            int addr = 0;  
            for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {  
                addr = addr * 10 + (s.charAt(segEnd) - '0');  
                if (addr <= 255) {  // 数值必须在 0 到 255 之间  
                    segments[segId] = addr;  
                    dfs(s, segId + 1, segEnd + 1);  
                } else {  
                    break;  // 如果大于 255，则不需要继续检查更长的段  
                }  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
        Solution14 solution = new Solution14();  
        System.out.println(solution.restoreIpAddresses("25525511135"));  
        System.out.println(solution.restoreIpAddresses("0000"));  
        System.out.println(solution.restoreIpAddresses("101023"));  
    }  
}
//Solution14 with Solution13
