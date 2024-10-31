package com.evan.study.leetcode.interview;

/**
 * Within the rated cargo weight of the company's standard transportation , there are two types of cargo.
 * For example, the weight of cargo A is weight wa, and the profit of transportation is profit pa.
 * The weight of cargo B is weight wb, and the profit of transportation is profit pb.
 * Each time for transport, the total weight of cargo is exactly the rated capacity T of the truck.
 * There must be two kinds of cargo on the truck at the same time.
 * Here we need a product to help the driver to calculate the maximum profit in a single transportation, with the inputs:
 * the weight of cargo A
 * the weight of cargo B
 * rated capacity T of the truck
 * profit of cargo A
 * profit of cargo B
 * <p>
 * Sample：
 * input as 10 8 36 15 7
 * output as 44
 * <p>
 * 在公司标准运输的额定货物重量范围内，有两种货物。
 * 例如，货物A的重量为wa，运输的利润为pa。
 * 货物B的重量为重量wb，运输利润为利润pb。
 * 每次运输时，货物的总重量正好是卡车的额定容量T。
 * 卡车上必须同时装载两种货物。
 * 在这里，我们需要一个产品来帮助司机计算单次运输的最大利润，包括输入：
 * 货物A的重量
 * 货物B的重量
 * 卡车的额定容量T
 * 货物利润A
 * 货物利润B
 * 样品：
 * 输入为10 8 36 15 7
 * 输出为44
 * <p>
 * 解题关键：（1）总重量正好是T （2）必须同时装载两种货物
 */
public class MaxTransportProfit {
    public static int getMaxProfit(int wa, int wb, int T, int pa, int pb) {
        int maxProfit = 0;

        // 枚举所有可能的货物A和货物B的组合
        for (int numA = 0; numA * wa <= T; numA++) {
            int remainingWeight = T - numA * wa;

            // 检查剩余重量是否能完全由货物B组成
            if (remainingWeight % wb == 0) {
                int numB = remainingWeight / wb;
                int currentProfit = numA * pa + numB * pb;
                maxProfit = Math.max(maxProfit, currentProfit);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int wa = 10; // 货物A的重量
        int wb = 8;  // 货物B的重量
        int T = 36;  // 卡车的额定容量
        int pa = 15; // 货物A的利润
        int pb = 7;  // 货物B的利润

        int maxProfit = getMaxProfit(wa, wb, T, pa, pb);
        System.out.println("最大利润为：" + maxProfit);
    }
}
