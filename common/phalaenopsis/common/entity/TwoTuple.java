/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity;

/**
 * @author yangw786
 *
 * 2017年5月5日下午3:01:50
 */
public class TwoTuple<A, B> {
    public final A first;
    public final B second;
     
    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }
}
