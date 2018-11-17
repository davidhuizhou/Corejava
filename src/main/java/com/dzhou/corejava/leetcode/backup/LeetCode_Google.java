package com.dzhou.corejava.leetcode.backup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class LeetCode_Google {



  /**
   * Problem 163. Missing Ranges
   *
   * https://leetcode.com/problems/missing-ranges/description/
   */
  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> result = new ArrayList<>();

    return result;
  }


  /**
   * Problem 298. Binary Tree Longest Consecutive Sequence
   *
   * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence
   * /description/
   */
  public int longestConsecutive(TreeNode root) {
    return dfs(root, null, 0);

  }

  private int dfs(TreeNode p, TreeNode parent, int length) {

    if (p == null) return length;

    length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;

    return Math.max(length, Math.max(dfs(p.left, p, length), dfs(p.right, p,
      length)));

  }


  /**
   * Problem 340. Longest Substring with At Most K Distinct Characters
   *
   * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct
   * -characters/description/
   */
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int ans = 0, i = 0;
    CharacterCounter counter = new CharacterCounter();

    for (int j = 0; j < s.length(); j++) {
      counter.add(s.charAt(j), 1);
      while (counter.size() > k) {
        counter.add(s.charAt(i), -1);
        if (counter.get(s.charAt(i)) == 0) {
          counter.remove(s.charAt(i));
        }
        i++;
      }
      ans = Math.max(ans, j - i + 1);


    }
    return ans;
  }


  private class CharacterCounter extends HashMap<Character, Integer> {
    public int get(Character k) {
      return this.containsKey(k) ? super.get(k) : 0;
    }

    public void add(Character k, int v) {
      this.put(k, get(k) + v);
    }
  }


  /**
   * Problem 388. Longest Absolute File Path
   *
   * https://leetcode.com/problems/longest-absolute-file-path/description/
   */
  public int lengthLongestPath(String input) {
    if (input == null || input.length() == 0)
      return 0;

    int max = 0;

    String[] arr = input.split("\n");

    Stack<Node> stack = new Stack<Node>();

    for (int i = 0; i < arr.length; i++) {
      String s = arr[i];

      int count = getCount(s);

      while (!stack.isEmpty() && count <= stack.peek().level) {
        stack.pop();
      }

      if (s.contains(".")) {
        if (stack.isEmpty()) {
          max = Math.max(max, s.length() - count);
        } else {
          max = Math.max(max, stack.peek().len + s.length() - count);
        }
      } else {
        if (stack.isEmpty()) {
          stack.push(new Node(count, s.length() - count + 1));
        } else {
          stack.push(new Node(count,
            stack.peek().len + s.length() - count + 1));
        }
      }
    }

    return max;
  }

  private int getCount(String s) {
    int count = 0;
    int j = 0;
    while (j < s.length() - 1) {
      //System.out.println("first " + s.substring(j, j+2));
      if (s.substring(j, j + 1).equals("\t")) {

        j++;
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private class Node {
    int level;
    int len;

    public Node(int lev, int len) {
      this.level = lev;
      this.len = len;
    }
  }


  /**
   * Problem 683. K Empty Slots
   *
   * https://leetcode.com/problems/k-empty-slots/description/
   */
  public int kEmptySlots(int[] flowers, int k) {
    int maxPosition = 0;
    for (int position : flowers) {
      maxPosition = Math.max(maxPosition, position);
    }

    if (flowers.length < maxPosition) return -1;

    boolean[] flowerState = new boolean[flowers.length];

    for (int day = 0; day < flowers.length; day++) {
      flowerState[flowers[day] - 1] = true;
      if (this.foundDay(flowerState, flowers[day] - 1, k)) {
        return day + 1;
      }
    }
    return -1;
  }

  boolean foundDay(boolean[] flowerState, int x, int k) {

    int left = x - k - 1;

    if (left >= 0 && flowerState[left]) {
      boolean hasBloom = false;
      for (int l = x - 1; l > left; l--) {
        if (flowerState[l]) {
          hasBloom = true;
          break;
        }
      }
      if (!hasBloom) {
        return true;
      }
    }


    int right = x + k + 1;

    if (right <= flowerState.length - 1 && flowerState[right]) {
      boolean hasBloom = false;
      for (int r = x + 1; r < right; r++) {
        if (flowerState[r]) {
          hasBloom = true;
          break;
        }
      }
      if (!hasBloom) {
        return true;
      }
    }


    return false;
  }


  public int kEmptySlots2(int[] flowers, int k) {
    TreeSet<Integer> active = new TreeSet();
    int day = 0;
    for (int flower : flowers) {
      day++;
      active.add(flower);
      Integer lower = active.lower(flower);
      Integer higher = active.higher(flower);
      if (lower != null && flower - lower - 1 == k ||
        higher != null && higher - flower - 1 == k)
        return day;
    }
    return -1;
  }


  /**
   * Problem 904 - Fruit Into Baskets
   *
   * https://leetcode.com/problems/fruit-into-baskets
   */
  public int totalFruit(int[] tree) {
    int ans = 0, i = 0;
    Counter count = new Counter();
    for (int j = 0; j < tree.length; j++) {
      count.add(tree[j], 1);
      while (count.size() >= 3) {
        count.add(tree[i], -1);
        if (count.get(tree[i]) == 0) {
          count.remove(tree[i]);
        }
        i++;
      }
      ans = Math.max(ans, j - i + 1);
    }
    return ans;
  }

  private class Counter extends HashMap<Integer, Integer> {
    public int get(int k) {
      return this.containsKey(k) ? super.get(k) : 0;
    }

    public void add(int k, int v) {
      this.put(k, get(k) + v);
    }
  }


  /**
   * Problem 681 - Next Closest Time
   *
   * https://leetcode.com/problems/next-closest-time
   */
  public String nextClosestTime(String time) {
    int cur = 60 * Integer.parseInt(time.substring(0, 2));
    cur += Integer.parseInt(time.substring(3));
    Set<Integer> allowed = new HashSet();
    for (char c : time.toCharArray())
      if (c != ':') {
        allowed.add(c - '0');
      }

    while (true) {
      cur = (cur + 1) % (24 * 60);
      int[] digits = new int[]{cur / 60 / 10, cur / 60 % 10, cur % 60 / 10,
        cur % 60 % 10};
      search:
      {
        for (int d : digits) if (!allowed.contains(d)) break search;
        return String.format("%02d:%02d", cur / 60, cur % 60);
      }
    }
  }


  public String nextClosestTime2(String time) {
    int start = 60 * Integer.parseInt(time.substring(0, 2));
    start += Integer.parseInt(time.substring(3));

    int ans = start;
    int elapsed = 24 * 60;
    Set<Integer> allowed = new HashSet();
    for (char c : time.toCharArray()) {
      if (c != ':') {
        allowed.add(c - '0');
      }
    }

    for (int h1 : allowed)
      for (int h2 : allowed) {
        if (h1 * 10 + h2 < 24) {
          for (int m1 : allowed)
            for (int m2 : allowed) {
              if (m1 * 10 + m2 < 60) {
                int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
                int canElapsed = Math.floorMod(cur - start, 24 * 60);
                if (0 < canElapsed && canElapsed < elapsed) {
                  ans = cur;
                  elapsed = canElapsed;
                }

              }
            }
        }
      }

    return String.format("%02d:%02d", ans / 60, ans % 60);

  }


  public String nextClosestTime3(String time) {

    String minutes = time.trim().substring(3);
    String hours = time.trim().substring(0, 2);

    for (int nextMinutes = this.strToInt(minutes) + 1; nextMinutes <= 59;
         nextMinutes++) {
      if (this.canCreateNumberFromStr(nextMinutes, time)) {
        return hours + ":" + (nextMinutes < 10 ? "0" :
          "") + String.valueOf(nextMinutes);
      }
    }

    for (int nextHour = this.strToInt(hours) + 1; nextHour <= 23; nextHour++) {
      if (this.canCreateNumberFromStr(nextHour, time)) {
        return (nextHour < 10 ? "0" : "") + String.valueOf(nextHour)
          + ":" + getSmallestNumber(time) + getSmallestNumber(time);
      }
    }

    return getSmallestNumber(time) + getSmallestNumber(time)
      + ":" + getSmallestNumber(time) + getSmallestNumber(time);


  }

  private int strToInt(final String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      result = 10 * result + s.charAt(i) - '0';
    }
    return result;
  }

  private String getSmallestNumber(final String s) {
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i))) {
        result = Math.min(result, s.charAt(i) - '0');
      }
    }
    return String.valueOf(result);
  }

  private boolean canCreateNumberFromStr(int num, final String s) {
    while (num > 0) {
      if (!s.contains(String.valueOf(num % 10))) {
        return false;
      }
      num /= 10;
    }
    return true;
  }


  /**
   * Problem 929. Unique Email Addresses
   *
   * https://leetcode.com/problems/unique-email-addresses/solution/
   */
  public int numUniqueEmails(String[] emails) {
    Set<String> seen = new HashSet();
    for (String email : emails) {
      int i = email.indexOf('@');
      String local = email.substring(0, i);
      String rest = email.substring(i);
      if (local.contains("+")) {
        local = local.substring(0, local.indexOf('+'));
      }
      local = local.replaceAll(".", "");
      seen.add(local + rest);
    }

    return seen.size();
  }

  private FilePath getNextFilePath(Stack<FilePath> stack, int level) {

    if (stack.isEmpty() || stack.peek().getLevel() < level) return null;

    FilePath current = null;

    while (!stack.isEmpty() && stack.peek().getLevel() >= level
      && (current == null || stack.peek().getLevel() < current.getLevel())) {

      current = combineFilePath(stack.pop(), current);

    }

    return current;
  }


  private Stack<FilePath> addToStack(String input) {
    Stack<FilePath> stack = new Stack<>();

    String[] entries = input.split("\n");

    for (String s : entries) {
      stack.push(toFilePath(s));
    }
    return stack;
  }

  private FilePath toFilePath(String input) {
    int level = 0;

    int i = 0;
    while (input.charAt(i) == '\t') {
      level++;
      i++;
    }

    return new FilePath(level, input.substring(i));

  }

  private FilePath combineFilePath(FilePath parent, FilePath child) {
    if (parent == null) return child;
    if (child == null) return parent;

    return new FilePath(parent.level, parent.getPath() + "/" + child.getPath());
  }


  private class FilePath {
    private int level;
    private String path;

    FilePath(int level, String path) {
      this.level = level;
      this.path = path;
    }

    public int getLevel() {
      return this.level;
    }

    public int getLength() {
      return this.path.length();
    }

    public String getPath() {
      return this.path;
    }

    public boolean isFile() {
      return this.path.contains(".");
    }


    public String toString() {
      return "FilePath (" + level + "," + path + "," + this.getLength() + ")";
    }
  }


  public static void main(String[] args) {
    LeetCode_Google lc = new LeetCode_Google();

//    int[] tree = new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3};
//    System.out.println(lc.totalFruit(tree));
//
//    System.out.println(lc.strToInt("19"));
//
//
//    System.out.println(lc.nextClosestTime("22:37"));
//    System.out.println(lc.nextClosestTime2("22:37"));

//    String input = "dir\n\tsubdir1\n\t\tfile1" +
//      ".ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//
//    System.out.println(lc.toFilePath("\t\tsubsubdir1"));
//
//    System.out.println(lc.lengthLongestPath(input));

//    String input =
//      "xsfmnybyflzibrewmfpxeishyvrrybkqzyuvoehcyqupsjdwqxipeuffza\n" +
//
// "\tqukdekacfnfbffvrierxupppsggiwtnzwzbspndjtkwpvyazvuwryiqzarqxahndmbsmdoqdjbhf\n\t\thxttkicjbwaufxcjohukkikjbeanaprhalztwmirwgjufmuzwvkrjcxlao\n\t\t\tbjklxudmjvutdvvfxrcmxhlhjfmxpnnljajiyykxfabyhfigcvytigjixprwfljxptmthqdatrywbhor\n\t\t\t\tcgbidwlfvxvdrvgjgufskqgfiqenfamliftawifqbgvrj.df\n\t\tsmvuctybaatovybpaikdykgnfup\n\t\t\thifdrcspamfxcyisdgfzojctqovfuibwqyeacqimkreepbxxmaobdoknzsxpmixtwqgbaomjdakjiboxvfkzrshmsgnnztpefhdmkpufkpe\n\t\t\t\tbyqrxvtcywrfqjfapcuyavsjytocsqjvqaydsokayrqpvpusocvtecpdijplxoeqwptojiqez\n\t\t\t\t\tphevcaiegyjatazjlchdekyflrvb\n\t\t\t\t\t\tpnlypaytrmcfpdrctoasorsekacbgoaxnxzfyagawlxblzgs.awotcscymhdfkpgvrgwxaubvmexortladnguaowueqsvrslpt\n\t\tiodafqcbkwenzatberygzaxqtyqagliqubslycjfi\n\t\t\txxgqzwettztlsbwcpyvzwfvouadztghuds\n\t\tzhaamlbteefxvfgeyqlclgemdvrlwzfipdzuzhkuzijuvplteftazxfebovibvesc\n\t\t\tpequtmuqkhdeqmfiqzwtixejwkbeindqwgthhwnmxpalhcrimpdicm\n\t\t\t\txvwfnkuyokpdbdmzveskiojmloxaejdfje\n\t\t\tblcxqhqnsfeipdmvrkbsjaxtludgpgkhvzpvqitqmmmxcduehzvorjlklohuoegkgkxmrmgcmjjbrksgvfuvtczvmuehuiohknxuprhv\n\t\t\t\tmypptgogbcplebtmyqq.jcvqucyhhkkhuqmjhfbmsu\n\t\t\tshqnzstctvneimqovetwgjgdcmkmdjvcwqqqbijttkdvgurwgarexuhwwdtofdfhegwqqctrceqpqppbibsowrowrogxkceixhrhezvdoeqiz\n\t\t\t\tpxeofhoqmtnfxpt\n\t\t\tczvaezhqoegrsztawyurvoisykkwbacbs\n\t\t\t\tvevxrpbktrorabkjqesrc\n\t\t\t\t\toepedwdqwhnfdagsjqfouaoebbgafxo\n\t\t\t\t\t\tfxxuqfimrmlcapqhrdvlqxtpknawyvemqaayt\n\t\t\t\t\t\t\thscsnqmwtkerzsj.xxbjimpktyde\n\t\t\t\t\t\trcccvisnnkdvbddhkxjgisfiyprahbahcqnuaqwiamcvcowjexcybtidoygftghlzfnazlisqvbdjyssiwqxoyeixiaarkoxmk\n\t\t\t\t\t\txmhvcovzbokmrvuwjvpmezpychjcoxmboodvalyaivdfszagagznnkqrtojopakxlnliqrzdpulmqpmfwdavmkny\n\t\t\t\t\t\t\totamcrfsrhlyea.poncevbxrlubqexyecgqfwloooqizyqjnggvyeuntn\n\t\t\t\t\t\tqjrjnfdgoywywacjwwsxxysbhtkigmctxkgqtwgvvckymox\n\t\t\t\tbwjpobitnsbtttubxegfwenxcylvuitcae\n\t\t\t\tearnonpgfobjlswpbmyjrhtdfemgmvxwfqezsnnwkdjummzckajnpogfdwfgoulnkcrfzntjkvqw\n\t\t\t\t\tnwpdzkjxgntcrupmcxupfignnesvkiyhuvzahycxnhymuexgyl\n\t\t\t\t\tcffosav.qkiasvmflxidldmunaxkuyctrjkkgizc\n\t\t\t\t\thebqzdcfevwegdyyiqysnwhpivbubpiaercdd\n\t\t\t\t\t\treakcufeykupejggvzjkvzfbbfnxoicpuehpwcukpayfjqdkswrrfidnurocgpijaymguoetnrchjwjcmwwrair\n\t\t\t\t\t\t\tvhjordwfsxdkeshuedcwvqemmulne\n\t\t\t\t\tnjbzerdjaedmhkibbbbiwtcgacbnjdovtofshnltesulkegkiikbczfstazvylxuzbaswejehjlowydsgwuptphrdwmbuxecpqpkw\n\t\t\t\t\t\tytesfsautcpbwctiocopsiohwrlxehzjqqfobqtbhmobzfpvdcdkfgzwssydolvfoknjgfncakbkytqiafutaktzjecwjvitzwqblfddhd\n\t\t\t\t\tjmafjmpoanjxmzopejmkgdoqqrf\n\t\t\t\t\t\tijg.bbepnarykmjjzdxelv"
//
//      +
//
// "\niyijkrotqfllzviqliyogotkkjimoyiywzlppdqucjuozvcbctmkhghwbdaltisurnakkcnjjbqidpbvzjmzvjgwzhuxqdjpffb\n\tkehcmvwwltycpicgwivvdfmlwhdivnemlbkgwzyoocavanktgrdjdvewroyoptfnfrcoohgrauxrmirrad\n\t\tmswgowvttxjhzqzsbykbbqpjccnoyjuujdaxcycuwhfdlzdzguqbahgesldolsazeu\nlgrnlmsjtbobdqqwyubnpwqoshvdokspgtssdaszrgzncecvxoxuaqxrvlwlaavhtvbdvvzclgzrgucfyigznbugmckgrr\n\twxpkqltcakmcxsvssyykbphfmwaecvsolauwpveckzjdrkhkrxqdqxgqxemlxqgfzwgvfinn\n\t\tccrqqbihnpnq.felbmahwnuzygcpskwepzwmmvhjqxopgpzcmujibymlpeiaqxkqwcyxvunqdjulqj\n\t\txyanuidxogqfltdihpczzpnpkkvudykeugrpdbjrfdmdjflxurlblkiibfuieohulroarsmotonlmuqmgwxdjkaqpkkehqdkidbcifdvap\n\t\t\tsreikpjtwdvnrnj\n\t\t\tbgffxxdmqgqetcwkhztvcruapodlhcwedxwzgbttqdgbsglux\n\t\t\t\tnxcdhmjrlwxvyn.dbjdunnplerlqnvpkrdrnumjbqdpnapvcnhqoolgnclaewiovqvfirbhcgqxlgcodewt\nozgxkcxyzbiecntuhgilgzerceqtlaardbqwmmkrejgjmjdyqbwsec\n\tyexaooxvzumffgttmhworazrnyrrwdrdavzgrjjvacavztzdmeekfxbyiggxzihyuxmtolwchn\n\t\tekqqigbydisdjmdqexxmteeihmznspkdxhysir\n\t\t\txfxpgtrhzwzblib.fokzoawhotgqiyucdlmmeuuarragzaqmuibkssqgzctkmgnnwpdxslchaepons\nmtyksjsaywreruhujeniflfvwoufoxpceydndnenpodmmzantehkkxieumgygktorgpbxiedofsiauymwirbfucmyr\npdnwrywazlmwlnqhydhonztzvljtdehtezxqnnimwdpejdxaucwuwzghniwdh\nyzhrlkejfkccpxzpptglajyppdpyakopnlckjsgxcwbvepaalwzrvavebxskcbpdhxk\nznsenudxhqerznhcrxbnicwylenldxyzykrzdpepzmhekaqfcjietqnbrvoqsviyndrnml\nwxnoufddvnspgjz\nawsaxewetiiijpsnvjtsfyudnvyecuhfejnp\npqyjczivzufnlefqlaigkan\nfsiwfwdroobnkycoajmwwcfvahwsmubqfqaxttvvcpoiqgqyrumphlszlyqwhnvuqdfkbbcybbb\nupevhottmmcjk\niogrlydgpogiqwyocxxyvqclhfwruhtsqxhvvqfxprzk\nlyyvnsrliwwdcmfbckbengrstwmdikdwundxsorplgtubdzhezlbfsjggydkfxhxyyikyeeahzoorvhwavlmkoxre\neyuhqonixeeqdnrsdtsmzamvedksjigdtturocfhjcziyy\noemllsytiyiiskrxwuouwtfdrcxmbupydvjkpvteomhwxpeawhfiyghllfragdyehsydj\npaqyvjnyecpmqydhvqcyik\nubhbqblxgikkskzuqrmhsolpvmyzbmvbrinobjzc\nictwircxfmbjalddybplvwpgz\nmlhdshyfmb\nuipplwljtwjryiavdmlefswjvaochlzxbxuoecykwllpkizwhkyvnhanyliizpfhprie\n\tumarzchvaguahpfjthdohjjp\n\t\tciylqhejzqbklpthqfoyarjvzgytmjudsjxuinxdhfjgyhbnpdggcuscbfgrjdjrlauuvnayraqtqitrfz\nshklyshqabstqpbgmuvvdfpqdvckupshebxhayvyrpxnpmijedhbgtccjxqduayvxwsraes\ngfcfntkmjmofxznzacyzeybjbcjlhaphapfuqn\nearkcabnciaonngouzdmuomrqgqkhyuqpxqyryhrmaxpwhytuurcyncxhwdrtsrcqvddvxjdsppxruvyywaagpfjvxsizaaexcakhifrrl\njgfphlsdocmxdqquxusflfzslkcnzqftbyvzfiqwqnztmubvvtokxedpvaauyyvcjefovwhecsdokiupplvxazkjnwgywjslejialxyoa\nkczvyunphmxlxsroqkdgvsmcsepcfmtsfsidccffxkxvdgzsevmeobrobsvkvmqu\nhdfbfuauzkaohuxwewgyrecybeobxnzaijpubmxpcwctbcocohnifraklrrxugsrggnzg\nrccmsddidsbnfupkjryqyupekjzwocbshhriiyzsrykktaswpwapwqpbgsmqq\ngvakvkbjceksvwdnkxxejikgubddfgfakcpjwqxyzdhrrnyidcvbbonpctqxegzqiwkksnhgqtyaejqobpyecrzlbxuubvtnkxgwio\nmxgiczcmygupfdvtnhxysclhcuaqamligbiglnotquyexsxcyoawozcqvvplnmmsdbaldszukrtkzvilslwjxetinvgapixoah\nctxhilqwtvyedrgeqqrqkzleyignvfipdaacxenhcwrrrhemyzxoxjjpargmjikmlealwljeb\nslwogagwahqdcvzjusdajaaarrndbqfwhojkuvxztlkogpijuwuwbczdyddbtlhsskrhejqczzd\nhdhkillpibshgjkjjvievlyhythrg\n\tiplrturrvuosavkfpiibdapdbqmlzycwbgdytrtmwdnuuet\nhdjrolachexjqfaczblrqsbphqpiiiloeiabmvsazrcsncshgluedsggmavrnmlrnmgoev\niebhuiarcpkxjbzjhsqdgtpkymetlzu\nhifxfdghrzdsrhchutexidvqv\nifyxiqneiskyfhknooqlakkybpflgchnwofegcuihmzadpqiacusznmwifrwbycqtsymugfzanmnwfiqkf\nmzfozjfdzpmsygxvbnkufbyirjbjoqaqfhicozdhbvdsiarrogklpbzrgtbvfkvrgyjevxkllkburhflxhokqxxsl\nckujtwoxsbpxmkfgz\nhlxndnqkjfanujqemnwzxvflwtlacdbplegpifewtinutmuzy\nyghsukdyhqyiyuykzyipocydgkeldjlobwjgylnpaqiyibdncwohyipqtzykxskefmfenn\nfieeffuopjbbrjkzetxzmiaybkxwnoksplflinzlori\nonbpkrpstgrfdgrvpqewxhjooewswpyksri\n\tizqntvlaojkzpzkqzkmfblohpnybrgvlhisdhwwsaadxmlmysjrxwcghjoskgaubikfthtiexzkwkwkvhgamjigksguoqjzhldrgjgufrdj\n\t\tkschoevfbpovzlecpktkkdshpzvckrlyrubduqpkrszqzfeptqwegaptsarcmiaenbuueyfszzbpcaacpxmgs\n\t\t\tflrhsztlpfxjzwywtwewelnowgtimeflklocjsrewqmhqmtrprzizbzxsehxpmdrewmpodqudtmxpsujzqgzcjaskspupkikoxcc\n\t\t\t\tcwyforrbvrdlrdkrfbxbrmxaeetu.nwrsumqsjqrujaztrwpdsytyihyepmakzxpoejtxburkhesbqvjfowxmqmvdgsrlqfmsqqvykcpggkwxn";
//


    String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
    System.out.println(input);


    System.out.println(lc.lengthLongestPath(input));

    int[] flowers = new int[]{1, 2, 3};
    System.out.println("day=" + lc.kEmptySlots(flowers, 0));

    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(2);
    root.right.right = new TreeNode(4);
    root.right.right.right = new TreeNode(5);

    System.out.println("longestConsecutive=" + lc.longestConsecutive(root));

    System.out.println("lengthOfLongestSubstringKDistinct=" + lc.lengthOfLongestSubstringKDistinct("eceba", 2));


    int[] nums = new int[]{0, 1, 3, 50, 75};
    System.out.println("findMissingRanges=" + lc.findMissingRanges(nums, 0,
      99));

    nums = new int[]{2147483647};

//    System.out.println("findMissingRanges=" + lc.findMissingRanges(nums, 0,
//      2147483647));


  }


}
