package com.xzk.wx.test;

import com.xzk.wx.util.TicketUtil;
import com.xzk.wx.util.TokenUtil;
import org.junit.Test;

public class TokenTest {

	@Test
	public void test1() throws Exception {
		String token1 = TokenUtil.getToken();
		System.out.println(token1);
		token1 = TokenUtil.getToken();
		System.out.println(token1);
		token1 = TokenUtil.getToken();
		System.out.println(token1);
		token1 = TokenUtil.getToken();
		System.out.println(token1);
	}
	@Test
	public void test2() throws Exception {
		String ticket = TicketUtil.getTicket();
		System.out.println(ticket);
		ticket = TicketUtil.getTicket();
		System.out.println(ticket);
		ticket = TicketUtil.getTicket();
		System.out.println(ticket);
	}

}
