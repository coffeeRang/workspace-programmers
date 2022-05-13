package heap.question1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestMain {

    /**
     * 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶다.
     * 섞인 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
     * 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복해서 섞는다.
     * 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution함수를 작성해 주세요
     *
     * 제약조건
     * scoville의 길이는 2 이상 1,000,000 이하입니다.
     * K는 0 이상 1,000,000,000 이하입니다.
     * scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
     * 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
     *
     * 입력값 〉
     *   	[1, 2, 3, 9, 10, 12], 7
     *
     *
     *   기댓값 〉
     *   	2
     *   입력값 〉
     *   	[1, 2, 3, 5, 8], 7
     *
     *
     *   기댓값 〉
     *   	3
     *
     *
    * @param args
     */
    public static void main(String[] args) {
        int answer = 0;
//        int[] scoville = {1, 2, 3, 9, 10, 12};
        int[] scoville = {1, 2, 3, 5, 8};
        int k = 7;
        answer =recursiveSolution(scoville, k, answer);
        System.out.println("결과: " + answer);
    }

    // scoville이 사이즈가 2이면
    public static int recursiveSolution(int[] scoville, int k, int answer) {
        int[] sortedArr = scoville;
        Arrays.sort(sortedArr);
        if (sortedArr[0] >= k) {
            return answer;
        }
        if (2 == sortedArr.length) {
            if (isScovilleOverK(sortedArr[0], sortedArr[1], k)) {
                answer++;
            } else {
                return answer = -1;
            }
            return answer;
        }
        if (sortedArr[0] >= k) {
            return answer;
        }

        int newScovile = mixedScoville(sortedArr[0], sortedArr[1]);
        List<Integer> sList = Arrays.stream(sortedArr).boxed().collect(Collectors.toList());
        sList.remove(0);
        sList.remove(0);
        List<Integer> newScovileList = new ArrayList<>();
        newScovileList.add(newScovile);
        newScovileList.addAll(sList);

        int[] integerArr = newScovileList.stream().mapToInt(i -> i).toArray();
        answer++;
        answer = recursiveSolution(integerArr, k, answer);
        return answer;
    }

    public static boolean isScovilleOverK(int num1, int num2, int k) {
        return mixedScoville(num1, num2) >= k ? true : false;
    }

    public static int mixedScoville(int num1, int num2) {
        return num1 + (num2 * 2);
    }
}
