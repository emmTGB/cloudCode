import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Map<Character, List<Character>> m = new HashMap<>();
        Map<Character, Integer> cnt = new HashMap<>();
        Set<Character> s = new HashSet<>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] a = line.split(" ");
        Arrays.sort(a);
        for (String t : a) {
            String[] tmp = t.split("->");
            char l = tmp[0].charAt(0), r = tmp[1].charAt(0);
            m.putIfAbsent(r, new ArrayList<>());
            m.get(r).add(l);
            cnt.put(l, cnt.getOrDefault(l, 0) + 1);
            if (!cnt.containsKey(r))
                cnt.put(r, 0);
            s.add(l);
            s.add(r);
        }

        // 开始遍历
        Queue<Character> q = new LinkedList<>();
        for (char c : cnt.keySet()) {
            if (cnt.get(c) == 0) {
                q.offer(c);
            }
        }
        List<Character> res = new ArrayList<>();
        while (!q.isEmpty()) {
            Character cur = q.poll();
            res.add(cur);
            for (Character d : m.getOrDefault(cur, Collections.emptyList())) {
                cnt.put(d, cnt.get(d) - 1);
                if (cnt.get(d) == 0) {
                    q.offer(d);
                }
            }
        }

        if (res.size() == s.size()) {
            for (Character c : res) {
                System.out.print(c);
                System.out.print(' ');
            }
        } else {
            System.out.println("NULL");
        }

        in.close();
    }
}