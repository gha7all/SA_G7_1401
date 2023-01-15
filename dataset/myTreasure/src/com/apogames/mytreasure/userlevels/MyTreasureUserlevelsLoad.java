/*
 * Copyright (c) 2005-2013 Dirk Aporius <dirk.aporius@gmail.com>
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.apogames.mytreasure.userlevels;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.apogames.mytreasure.MyTreasureActivity;
import com.apogames.mytreasure.MyTreasureConstants;

public class MyTreasureUserlevelsLoad {
	
	private ArrayList<String> levels;
	private ArrayList<String> names;
	private ArrayList<Integer> count;
	private ArrayList<Integer> all;
	private ArrayList<Integer> id;
	private ArrayList<Integer> fun;
	private ArrayList<Integer> creative;
	private ArrayList<Integer> easy;
	private ArrayList<Integer> hard;
	private ArrayList<Float> times;
	private ArrayList<Float> curTimes;

	private static MyTreasureUserlevelsLoad singleInstance = new MyTreasureUserlevelsLoad();

	public static MyTreasureUserlevelsLoad getInstance() {
		if (null == singleInstance) {
			singleInstance = new MyTreasureUserlevelsLoad();
		}
		return singleInstance;
	}

	private MyTreasureUserlevelsLoad() {
		this.levels = new ArrayList<String>();
		this.names = new ArrayList<String>();
		this.count = new ArrayList<Integer>();
		this.all = new ArrayList<Integer>();
		this.id = new ArrayList<Integer>();
		this.easy = new ArrayList<Integer>();
		this.hard = new ArrayList<Integer>();
		this.fun = new ArrayList<Integer>();
		this.creative = new ArrayList<Integer>();
		this.times = new ArrayList<Float>();
		this.curTimes = new ArrayList<Float>();
		clear();
	}

	public ArrayList<Float> getTimes() {
		return this.times;
	}

	public ArrayList<Float> getCurTimes() {
		return this.curTimes;
	}

	public ArrayList<String> getLevels() {
		return this.levels;
	}
	
	public ArrayList<String> getNames() {
		return this.names;
	}

	public ArrayList<Integer> getCount() {
		return this.count;
	}

	public ArrayList<Integer> getAll() {
		return this.all;
	}

	public ArrayList<Integer> getId() {
		return this.id;
	}

	public ArrayList<Integer> getFun() {
		return this.fun;
	}

	public ArrayList<Integer> getCreative() {
		return this.creative;
	}

	public ArrayList<Integer> getEasy() {
		return this.easy;
	}

	public ArrayList<Integer> getHard() {
		return this.hard;
	}

	private void clear() {
		this.all.clear();
		this.levels.clear();
		this.id.clear();
		this.names.clear();

		this.fun.clear();
		this.easy.clear();
		this.hard.clear();
		this.creative.clear();
		
		this.count.clear();
		this.times.clear();
		this.curTimes.clear();
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public boolean save(String level) {
		if (MyTreasureActivity.isOnline()) {
			try {
				URL url;
				URLConnection urlConn;
				DataOutputStream printout;
				DataInputStream input;

				url = new URL(MyTreasureConstants.USERLEVELS_SAVEPHP);
				urlConn = url.openConnection();

				urlConn.setDoInput(true);
				urlConn.setDoOutput(true);

				urlConn.setUseCaches(false);
				urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

				printout = new DataOutputStream(urlConn.getOutputStream());
				
				String content = "level=" + URLEncoder.encode(String.valueOf(level), "UTF-8");
				printout.writeBytes(content);
				printout.flush();
				printout.close();

				input = new DataInputStream(urlConn.getInputStream());
				String str;
				while (null != ((str = input.readLine()))) {
				}
				input.close();
				return true;
			} catch (MalformedURLException me) {
				System.err.println("MalformedURLException: " + me);
			} catch (IOException ioe) {
				System.err.println("IOException: " + ioe.getMessage());
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean load() {
		clear();
		if (MyTreasureActivity.isOnline()) {
			try {
				URL url;
				URLConnection urlConn;
				DataOutputStream printout;
				DataInputStream input;

				url = new URL(MyTreasureConstants.USERLEVELS_GETPHP);
				urlConn = url.openConnection();

				urlConn.setDoInput(true);
				urlConn.setDoOutput(true);

				urlConn.setUseCaches(false);
				urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

				printout = new DataOutputStream(urlConn.getOutputStream());

				String content = "Get=" + URLEncoder.encode("", "UTF-8");

				printout.writeBytes(content);
				printout.flush();
				printout.close();
				
				input = new DataInputStream(urlConn.getInputStream());
				String str;
				int i = 0;
				while (null != ((str = input.readLine()))) {
					if (i % 11 == 0) {
						this.levels.add(str);
					} else if (i % 11 == 1) {
						this.times.add(Float.valueOf(str));
					} else if (i % 11 == 2) {
						this.count.add(Integer.valueOf(str));
					} else if (i % 11 == 3) {
						this.all.add(Integer.valueOf(str));
					} else if (i % 11 == 4) {
						this.names.add(str);
					} else if (i % 11 == 5) {
						this.fun.add(Integer.valueOf(str));
					} else if (i % 11 == 6) {
						this.easy.add(Integer.valueOf(str));
					} else if (i % 11 == 7) {
						this.hard.add(Integer.valueOf(str));
					} else if (i % 11 == 8) {
						this.creative.add(Integer.valueOf(str));
					} else if (i % 11 == 9) {
						this.id.add(Integer.valueOf(str));
					} else if (i % 11 == 10) {
						this.curTimes.add(Float.valueOf(str));
					}
					i = i + 1;
				}
				input.close();
				return true;
			} catch (MalformedURLException me) {
				System.err.println("MalformedURLException: " + me);
			} catch (IOException ioe) {
				System.err.println("IOException: " + ioe.getMessage());
			}
		}
		return false;
	}
}
