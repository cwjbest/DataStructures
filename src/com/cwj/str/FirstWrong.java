package com.cwj.str;

/**
 * Created by cwj on 18-4-3.
 * 你是产品经理，目前正在领导一个团队开发一个新产品。不幸的是，您的产品的最新版本没有通过质量检查。
 * 由于每个版本都是基于之前的版本开发的，所以错误版本之后的所有版本都是不好的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出第一个错误的版本，导致下面所有的错误。
 * 你可以通过 bool isBadVersion(version) 的接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。您应该尽量减少对 API 的调用次数。
 * <p>
 * 二分查找传统取中的算法：mid = (start + end)/2
 * 但是遇到 int 时，相加可能会溢出，所以转换一下
 * start + end = start + start  + end - start
 * 所以写成
 * mid = start + (end - start)/2
 * 就能避开 start + end　溢出
 */
public class FirstWrong {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
//            if (isBadVersion(mid)) {
//                end = mid - 1;
//
//            } else {
//                start = mid + 1;
//            }
        }
        return start;
    }
}