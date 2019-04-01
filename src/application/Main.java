package application;
	
import Commands.ExitCommand;
import Commands.RunExample;
import Controller.Controller;
import Domain.PrgState;
import Expression.*;
import Repository.Repo;
import Statement.*;
import View.TextMenu;

public class Main {
	
	public static TextMenu init() {
		
		/* 	v = 1; 
			print(v)
		*/
		Repo repo1 = new Repo("src/Logs/Repository/Problem1.txt");
		PrgState s1 = new PrgState();
		AssignStmt as1 = new AssignStmt("v",new ConstExp(1));
		PrintStmt ps1 = new PrintStmt(new VarExp("v"));
		s1.addStatement(new CompStmt(as1, ps1));
		repo1.addPrgState(s1);
		Controller c1= new Controller(repo1);
		String ex1 = "\tv = 1;\n\tprint(v);\n";
		
		/*
		   a = 2 + 3 * 4;
		   b = a - 8 / 2 + 5;
		   Print(b)
		*/
		
		Repo repo2 = new Repo("src/Logs/Repository/Problem2.txt");
		PrgState s2 = new PrgState();
		AssignStmt as2 = new AssignStmt("a",
										new ArithExp(	'+',
														new ConstExp(2),
														//null,
														new ArithExp(	'*',
																		new ConstExp(3),
																		new ConstExp(4)
																	)
													)
										);
		AssignStmt as3 = new AssignStmt("b",
										new ArithExp('+',
													new ArithExp('-',
																new VarExp("a"),
																	new ArithExp('/',
																					new ConstExp(8),
																					//new ConstExp(0)
																					new ConstExp(2)
																				)
																),
													new ConstExp(5)
												)
										);
		PrintStmt ps2 = new PrintStmt(new VarExp("b"));
		
		s2.addStatement(new CompStmt(new CompStmt(as2,as3), ps2));
		
		repo2.addPrgState(s2);
		
		Controller c2 = new Controller(repo2);
		
		String ex2 ="\ta = 2 + 3 * 4;\r\n" + 
					"\tb = a - 8 / 2 + 5;\r\n" + 
					"\tprint(b);\n";
		/*
	   	c = 5 - 1;
	  	If c Then 
	  		v = 1 
	  	Else 
	  		v = 2;
	  	Print(v)
		*/
		Repo repo3 = new Repo("src/Logs/Repository/Problem3.txt");
		PrgState s3 = new PrgState();
		PrintStmt ps3 = new PrintStmt(new VarExp("v"));
		IfStmt if1 = new IfStmt(new VarExp("c"),
								new AssignStmt("v", new ConstExp(1)),
								new AssignStmt("v", new ConstExp(2))
								);
		AssignStmt as4 = new AssignStmt("c",
										new ArithExp('-',
													new ConstExp(5),
													new ConstExp(1)
													)
										);
		s3.addStatement(new CompStmt(as4, new CompStmt(if1,ps3)));
		repo3.addPrgState(s3);
		Controller c3 = new Controller(repo3);
		String ex3 ="\tc = 5 - 1;\n" +
					"\tif (c) {\n" +
					"\t\tv = 1;\n" +
					"\t}\n" +
					"\telse {\n" +
					"\t\tv = 2;\n" +
					"\t}\n" +
					"\tprint (v);\n";
		
		/*openRFile(var_f,"input.txt");
		readFile(var_f,var_c);
		print(var_c);
		if (var_c) {
			readFile(var_f,var_c);
			print(var_c)
		}
		else { 
			print(0)
		}
		closeRFile(var_f)*/
		
		Repo repo4 = new Repo("src/Logs/Repository/Problem4.txt");
		PrgState s4 = new PrgState();
		OpenReadFileStmt orfs1 = new OpenReadFileStmt("src/Input/input.txt", "var_f");
		ReadFileStmt rfs1 = new ReadFileStmt(new VarExp("var_f"), "var_c");
		PrintStmt ps4 = new PrintStmt(new VarExp("var_c"));
		CompStmt cs1 = new CompStmt(rfs1,ps4);
		ReadFileStmt rfs2 = new ReadFileStmt(new VarExp("var_f"), "var_c");
		PrintStmt ps5 = new PrintStmt(new VarExp("var_c"));
		CompStmt cs2 = new CompStmt(rfs2, ps5);
		PrintStmt ps6 = new PrintStmt(new ConstExp(0));
		IfStmt if2 = new IfStmt(new VarExp("var_c"), cs2, ps6);
		CloseReadFileStmt crfs1 = new CloseReadFileStmt(new VarExp("var_f"));
		
		s4.addStatement(crfs1);
		s4.addStatement(if2);
		s4.addStatement(cs1);
		s4.addStatement(orfs1);
		
		repo4.addPrgState(s4);
		
		Controller c4 = new Controller(repo4);
		
		String ex4 ="\topenReadFile(var_f,\"input.txt\");\n" +
					"\treadFile(var_f,var_c);\n" +
					"\tprint(var_c);\n" +
					"\tif (var_c) {\n" +
					"\t\treadFile(var_f,var_c);\n" +
					"\t\tprint(var_c);\n" + 
					"\t}\n" +
					"\telse {\n" +
					"\t\tprint(0)\n" +
					"\t}\n" +
					"\tcloseReadFile(var_f);\n";
		
		/* openRFile(var_f,"input.txt"); 
		 * readFile(var_f,var_c);
		 * print(var_c); (
		 * if (var_c) { 
		 * 		readFile(var_f + 2,var_c);
		 * 		print(var_c) 
		 * }
		 * else {
		 * 		print(0)); 
		 * }
		 * closeRFile(var_f)
		 */
		
		Repo repo5 = new Repo("src/Logs/Repository/Problem5.txt");
		PrgState s5 = new PrgState();
		OpenReadFileStmt orfs2 = new OpenReadFileStmt("src/Input/input.txt", "var_f");
		ReadFileStmt rfs3 = new ReadFileStmt(new VarExp("var_f"), "var_c");
		PrintStmt ps7 = new PrintStmt(new VarExp("var_c"));
		CompStmt cs3 = new CompStmt(rfs3,ps7);
		ReadFileStmt rfs4 = new ReadFileStmt(new ArithExp('+',new VarExp("var_f"),new ConstExp(2)), "var_c");
		PrintStmt ps8 = new PrintStmt(new VarExp("var_c"));
		CompStmt cs4 = new CompStmt(rfs4, ps8);
		PrintStmt ps9 = new PrintStmt(new ConstExp(0));
		IfStmt if3 = new IfStmt(new VarExp("var_c"), cs4, ps9);
		CloseReadFileStmt crfs2 = new CloseReadFileStmt(new VarExp("var_f"));
		
		s5.addStatement(crfs2);
		s5.addStatement(if3);
		s5.addStatement(cs3);
		s5.addStatement(orfs2);
		
		repo5.addPrgState(s5);
		
		Controller c5 = new Controller(repo5);
		
		String ex5 ="\topenReadFile(var_f,\"input.txt\");\n" +
					"\treadFile(var_f,var_c);\n" +
					"\tprint(var_c);\n" +
					"\tif (var_c) {\n" +
					"\t\treadFile(var_f + 2,var_c);\n" +
					"\t\tprint(var_c);\n" + 
					"\t}\n" +
					"\telse {\n" +
					"\t\tprint(0)\n" +
					"\t}\n" +
					"\tcloseReadFile(var_f);\n";
		
		/* 
		  v=10;
		  new(v,20);
		  new(a,22);
		  print(100+rH(v));
		  print(100+rH(a))		 *
		*/
		
		Repo repo6 = new Repo("src/Logs/Repository/Problem6.txt");
		PrgState s6 = new PrgState();
		
		IStmt st1 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
								new CompStmt(new HeapAllocStmt("v", new ConstExp(20)),
											new CompStmt(new HeapAllocStmt("a", new ConstExp(22)),
														new CompStmt(
																	new PrintStmt(	new ArithExp('+',
																					new ConstExp(100),
																					new HeapReadExp("v")
																								)
																				),
																	new PrintStmt(	new ArithExp('+',
																					new ConstExp(100),
																					new HeapReadExp("a")
																								)
																				)
																	)
														)
											)
								);
		s6.addStatement(st1);
		repo6.addPrgState(s6);
		Controller c6 = new Controller(repo6);
		
		String ex6 =
				"\tv = 10;\n" +
				"\tnew(v,20);\n" +
				"\tnew(a,22);\n" +
				"\tprint(100+rH(v));\n" +
				"\tprint(100+rH(a));\n";
		
		/*
		 v=10;
		 new(v,20);
		 new(a,22);
		 wH(a,30);
		 print(a);
		 print(rH(a));
		 a=0
		 */
		
		Repo repo7 = new Repo("src/Logs/Repository/Problem7.txt");
		PrgState s7 = new PrgState();
		
		IStmt st2 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
				new CompStmt(new HeapAllocStmt("v", new ConstExp(20)),
							new CompStmt(new HeapAllocStmt("a", new ConstExp(22)),
										new CompStmt(new HeapWriteStmt("a", new ConstExp(30)),
													new CompStmt(new PrintStmt(new VarExp("a")),
																 new CompStmt(new PrintStmt(new HeapReadExp("a")),
																		 	  new AssignStmt("a",new ConstExp(0))
																		 	)
																)
													)
										)
							)
				);
		s7.addStatement(st2);
		repo7.addPrgState(s7);
		Controller c7 = new Controller(repo7);
		
		String ex7 =
				"\tv = 10;\n" +
				"\tnew(v,20);\n" +
				"\tnew(a,22);\n" +
				"\twH(a,30);\n" +
				"\tprint(a);\n" +
				"\tprint(rH(a));\n" +
				"\ta = 0;\n";
		
		/*
		a = 10,
		new(a,20);
		print(rH(a));
		a = 100;
		print(rH(a))
		*/
		
		Repo repo8 = new Repo("src/Logs/Repository/Problem8.txt");
		PrgState s8 = new PrgState();
		
		IStmt st3 = new CompStmt(new AssignStmt("a", new ConstExp(10)),
						new CompStmt(new HeapAllocStmt("a", new ConstExp(20)),
							new CompStmt(new PrintStmt(new HeapReadExp("a")),
									new CompStmt(new AssignStmt("a", new ConstExp(100)),
												 new PrintStmt(new HeapReadExp("a"))
												)
										)
									)
								);
		s8.addStatement(st3);
		repo8.addPrgState(s8);
		Controller c8 = new Controller(repo8);
		
		String ex8 =
				"\ta = 10;\n" +
				"\tnew(a,20);\n" +
				"\tprint(rH(a));\n" +
				"\ta = 100;\n" +
				"\tprint(rH(a));\n";
		
		/*
		 openRFile(var_f,\"input.txt\"); 
		 readFile(var_f,var_c);
		 print(var_c); 
		 (if var_c then 
		 	readFile(var_f,var_c);
		 	print(var_c)
		else 
			print(0))				WHITOUT CLOUSE
		*/ 
		
		Repo repo9 = new Repo("src/Logs/Repository/Problem9.txt");

		PrgState s9 = new PrgState();
		OpenReadFileStmt orfs3 = new OpenReadFileStmt("src/Input/input.txt", "var_f");
		ReadFileStmt rfs11 = new ReadFileStmt(new VarExp("var_f"), "var_c");
		PrintStmt ps12 = new PrintStmt(new VarExp("var_c"));
		CompStmt cs12 = new CompStmt(rfs11, ps12);
		ReadFileStmt rfs12 = new ReadFileStmt(new VarExp("var_f"), "var_c");
		PrintStmt ps13 = new PrintStmt(new VarExp("var_c"));
		CompStmt cs13 = new CompStmt(rfs12, ps13);
		PrintStmt ps14 = new PrintStmt(new ConstExp(0));
		IfStmt if12 = new IfStmt(new VarExp("var_c"), cs13, ps14);


		s9.addStatement(if12);
		s9.addStatement(cs12);
		s9.addStatement(orfs3);
		repo9.addPrgState(s9);
		Controller c9 = new Controller(repo9);
		
		String ex9 ="\topenReadFile(var_f,\"input.txt\");\n" +
				"\treadFile(var_f,var_c);\n" +
				"\tprint(var_c);\n" +
				"\tif (var_c) {\n" +
				"\t\treadFile(var_f,var_c);\n" +
				"\t\tprint(var_c);\n" + 
				"\t}\n" +
				"\telse {\n" +
				"\t\tprint(0)\n" +
				"\t}\n";
		
		/*a=5, 
		 while (a > 0) { a = a - 1; print (a)}
		 */
		
		Repo repo10 = new Repo("src/Logs/Repository/Problem9.txt");
		PrgState s10 = new PrgState();
		
		AssignStmt as12 = new AssignStmt("a", new ConstExp(5));
		CompStmt cs14 = new CompStmt(new AssignStmt("a", 
									 				new ArithExp('-',
									 							new VarExp("a"),
									 							new ConstExp(1))),
												    new PrintStmt(new VarExp("a"))
									);
		WhileStmt w1 = new WhileStmt(new BoolExp(">", new VarExp("a"), new ConstExp(0)), cs14);
		
		s10.addStatement(w1);
		s10.addStatement(as12);
		
		repo10.addPrgState(s10);
		Controller c10 = new Controller(repo10);
		
		String ex10 ="\ta = 5;\n" +
					 "\twhile (a > 0) { a = a - 1; print(a) }\n";
		
		/*
		a=10+(2<6), 
		print(a)
		*/
		
		Repo repo11 = new Repo("src/Logs/Repository/Problem11.txt");
		PrgState s11 = new PrgState();
		
		CompStmt cs16 = new CompStmt(new AssignStmt ("a",
													new ArithExp('+',
																 new ConstExp(10),
																 new BoolExp("<",
																		 	new ConstExp(2),
																		 	new ConstExp(6)
																		 	)
																) 
													),
									 new PrintStmt(new VarExp("a"))
									);
		s11.addStatement(cs16);
		repo11.addPrgState(s11);
		Controller c11 = new Controller(repo11);
		
		String ex11 = "\ta = 10 + (2 < 6);\n" +
				 	  "\tprint(a)\n";
	
		/* v = 10;
		 * new(a,22); 
		 * fork(wH(a, 30)
		 * 		v = 32;
		 * 		print(v);
		 * 		print(rH(a))
		 * 		);
		 * print(v);
		 * print(rH(a))
		 */
		
		
		Repo repo12 = new Repo("src/Logs/Repository/Problem12.txt");
		PrgState s12 = new PrgState();
		CompStmt cs121 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
									  new HeapAllocStmt("a", new ConstExp(22))
									 );
		CompStmt cs122 = new CompStmt(new HeapWriteStmt("a", new ConstExp(30)),
									  new CompStmt(new AssignStmt("v", new ConstExp(32)),
											  	   new CompStmt(new PrintStmt(new VarExp("v")),
											  			   		new PrintStmt(new HeapReadExp("a"))
											  			   		)
											  	  )
									 );
		ForkStmt fs1 = new ForkStmt(cs122);
		CompStmt cs124 = new CompStmt(new PrintStmt(new VarExp("v")),
									  new PrintStmt(new HeapReadExp("a"))
				 					 );
		
		
		s12.addStatement(cs124);
		s12.addStatement(fs1);
		s12.addStatement(cs121);
		repo12.addPrgState(s12);
		Controller c12 = new Controller(repo12);
		
		String ex12 = 
				"\tv = 10;\n" +
				"\tnew(a, 22);\n" +
				"\tfork(wH(a, 30)\n" +
				"\t\tv = 32;\n" +
				"\t\tprint(v);\n" +
				"\t\tprint(rH(a));\n" + 
				"\t\t)\n" +
				"\tprint(v);\n" +
				"\tprint(rH(a))\n";
		
		/*
		   a = 1;
		   b = 2;
		   c = 5;
		   switch(a * 10)
		   		(case (b * c) print(a) print(b))
		   		(case (10) print(100) print(200))
		   		(default print(300))
		   	print(300)
		*/
		
		Repo repo13 = new Repo("src/Logs/Repository/Problem13.txt");
		PrgState s13 = new PrgState();
		AssignStmt as131 = new AssignStmt("a", new ConstExp(1));
		AssignStmt as132 = new AssignStmt("b", new ConstExp(2));
		AssignStmt as133 = new AssignStmt("c", new ConstExp(5));
		SwitchStmt sw1 = new SwitchStmt(new ArithExp('*', new VarExp("a"), new ConstExp(10)),
										new ArithExp('*', new VarExp("b"), new VarExp("c")),
										new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
										new ConstExp(10),
										new CompStmt(new PrintStmt(new ConstExp(100)), new PrintStmt( new ConstExp(200))),
										new PrintStmt(new ConstExp(100))
										);
		PrintStmt pr131 = new PrintStmt(new ConstExp(300));
		
		s13.addStatement(pr131);
		s13.addStatement(sw1);
		s13.addStatement(as133);
		s13.addStatement(as132);
		s13.addStatement(as131);
		
		repo13.addPrgState(s13);
		
		Controller c13 = new Controller(repo13);
		
		String ex13 ="\ta = 1;\r\n" + 
					"\tb = 2;\r\n" + 
					"\tc = 5;\r\n" +
					"\tswitch(a * 10)\r\n" +
					"\t\t(case (b * c) print(a); print(b))\r\n" +
					"\t\t(case (10) print(100); print(200))\r\n" +
					"\t\t(default print(300))\r\n" +
					"\tprint(300);\n";
		
		/*
		   a = 1; b = 2;
		   c = (a,b)?100:200;
		   print(c);
		   c = (b,b)?100:200;
		   print(c);
		*/
		
		Repo repo14 = new Repo("src/Logs/Repository/Problem14.txt");
		PrgState s14 = new PrgState();
		AssignStmt as141 = new AssignStmt("a", new ConstExp(1));
		AssignStmt as142 = new AssignStmt("b", new ConstExp(2));
		CondAssignStmt cas1 = new CondAssignStmt("c",
												 new VarExp("a"),
												 new VarExp("b"),
												 new ConstExp(100),
												 new ConstExp(200)
												);
		PrintStmt pr141 = new PrintStmt(new VarExp("c"));
		CondAssignStmt cas2 = new CondAssignStmt("c",
												  new VarExp("b"),
												  new VarExp("b"),
												 new ConstExp(100),
												 new ConstExp(200)
												);
		PrintStmt pr142 = new PrintStmt(new VarExp("c"));
		
		s14.addStatement(pr142);
		s14.addStatement(cas2);
		s14.addStatement(pr141);
		s14.addStatement(cas1);
		s14.addStatement(as142);
		s14.addStatement(as141);
		
		repo14.addPrgState(s14);
		
		Controller c14 = new Controller(repo14);
		
		String ex14 ="\ta = 1;\r\n" + 
					"\tb = 2;\r\n" + 
					"\tc = (a,b)?100:200;\r\n" +
					"\tprint(c)\r\n" +
					"\tc = (b,b)?100:200\r\n" +
					"\tprint(300);\n";
		
		//v = 10;
		//(fork (v = v - 1; v = v - 1; print(v)); sleep(10); print(v*10))
		
		
		Repo repo15 = new Repo("src/Logs/Repository/Problem15.txt");
		PrgState s15 = new PrgState();
		
		AssignStmt as150 = new AssignStmt("v", new ConstExp(10));
		AssignStmt as151 = new AssignStmt("v",
										  new ArithExp('-',
												  		new VarExp("v"),
												  		new ConstExp(1)
												  	  )
										 );
		
		PrintStmt ps151 = new PrintStmt(new VarExp("v"));
		
		PrintStmt ps152 = new PrintStmt(new ArithExp('*',
													 new VarExp("v"),
													 new ConstExp(10)
													)
										);
		
		
		
		CompStmt cs151 = new CompStmt(as151, new CompStmt(as151, ps151));
		ForkStmt fs151 = new ForkStmt(cs151);
		SleepStmt sl1 = new SleepStmt(new ConstExp(10));
		
		
		s15.addStatement(ps152);
		s15.addStatement(sl1);
		s15.addStatement(fs151);
		s15.addStatement(as150);
		
		repo15.addPrgState(s15);
		Controller c15 = new Controller(repo15);
		
		String ex15 = 
				"\tv = 10;\n" +
				"\t(fork (v = v-1; v = v-1; print(v));\n" +
				"\tsleep(10);" +
				"\tprint(v*10)";
		
		/*v=0;
		(repeat (fork(print(v);v=v-1);v=v+1) until v==3);
		x=1;y=2;z=3;w=4;
		print(v*10)
		*/
		Repo repo16 = new Repo("src/Logs/Repository/Problem16.txt");
		PrgState s16 = new PrgState();
		
		AssignStmt as161 = new AssignStmt("v", new ConstExp(0));
		AssignStmt as162 = new AssignStmt("v",
										  new ArithExp('-',
												  		new VarExp("v"),
												  		new ConstExp(1)
												  	  )
										 );
		AssignStmt as163 = new AssignStmt("v",
											new ArithExp('+',
														new VarExp("v"),
														new ConstExp(1)
														)
										);
		
		PrintStmt ps161 = new PrintStmt(new VarExp("v"));
		
		PrintStmt ps162 = new PrintStmt(new ArithExp('*',
													 new VarExp("v"),
													 new ConstExp(10)
													)
										);
		AssignStmt as164 = new AssignStmt("x", new ConstExp(1));
		AssignStmt as165 = new AssignStmt("y", new ConstExp(2));
		AssignStmt as166 = new AssignStmt("z", new ConstExp(3));
		AssignStmt as167 = new AssignStmt("w", new ConstExp(4));
		
		
		CompStmt cs161 = new CompStmt(ps161,as162);
		ForkStmt fs161 = new ForkStmt(cs161);
		RepeatStmt rs1 = new RepeatStmt(new CompStmt(fs161, as163),new BoolExp("==",
														  new VarExp("v"),
														  new ConstExp(3)
														 )
										);
		
		
		s16.addStatement(ps162);
		s16.addStatement(as167);
		s16.addStatement(as166);
		s16.addStatement(as165);
		s16.addStatement(as164);
		s16.addStatement(rs1);
		s16.addStatement(as161);
		
		repo16.addPrgState(s16);
		Controller c16 = new Controller(repo16);
		
		String ex16 = 
				"\tv = 0;\n" +
				"\t(repeat (fork (print(v);v = v-1;) v = v+1) until v==3\n" +
				"\tx = 1; y = 2; z = 3; w = 4" +
				"\tprint(v*10)";
		
		// cyclic-barrier

				// new(v1,2);new(v2,3);new(v3,4);newBarrier(cnt,rH(v2));
				// fork(await(cnt);wh(v1,rh(v1)*10));print(rh(v1)));
				// fork(await(cnt);wh(v2,rh(v2)*10));wh(v2,rh(v2)*10));print(rh(v2)));
				// await(cnt); print(rH(v3))

		PrgState p17 = new PrgState();
		Repo repo17 = new Repo("src/Logs/Repository/Problem17.txt");
		
		HeapAllocStmt ha121 = new HeapAllocStmt("v1", new ConstExp(2));
		HeapAllocStmt ha122 = new HeapAllocStmt("v2", new ConstExp(3));
		HeapAllocStmt ha123 = new HeapAllocStmt("v3", new ConstExp(4));
		NewBarrierStmt nb121 = new NewBarrierStmt("cnt", new HeapReadExp("v2"));

		CompStmt cs120 = new CompStmt(ha121,
				new CompStmt(ha122, new CompStmt(ha123, nb121)));

		AwaitStmt aws121 = new AwaitStmt("cnt");
		HeapWriteStmt hw121 = new HeapWriteStmt("v1",
				new ArithExp('*', new HeapReadExp("v1"), new ConstExp(10)));
		PrintStmt ps121 = new PrintStmt(new HeapReadExp("v1"));
		CompStmt cs171 = new CompStmt(aws121, new CompStmt(hw121, ps121));
		ForkStmt fs121 = new ForkStmt(cs171);

		AwaitStmt aws122 = new AwaitStmt("cnt");
		HeapWriteStmt hw122 = new HeapWriteStmt("v2",
				new ArithExp('*', new HeapReadExp("v2"), new ConstExp(10)));
		HeapWriteStmt hw123 = new HeapWriteStmt("v2",
				new ArithExp('*', new HeapReadExp("v2"), new ConstExp(10)));
		PrintStmt ps122 = new PrintStmt(new HeapReadExp("v2"));
		CompStmt cs172 = new CompStmt(aws122,
				new CompStmt(hw122, new CompStmt(hw123, ps122)));
		ForkStmt fs122 = new ForkStmt(cs172);

		AwaitStmt aws123 = new AwaitStmt("cnt");

		PrintStmt ps123 = new PrintStmt(new HeapReadExp("v3"));

		

		p17.addStatement(ps123);
		p17.addStatement(aws123);
		p17.addStatement(fs122);
		p17.addStatement(fs121);
		p17.addStatement(cs120);
		repo17.addPrgState(p17);

		Controller c17 = new Controller(repo17);
		
		String ex17 = "\tnew(v1,2);new(v2,3);new(v3,4);newBarrier(cnt,rH(v2));\n" + 
					  "\tfork(await(cnt);wh(v1,rh(v1)*10));print(rh(v1)));\n" + 
					  "\tfork(await(cnt);wh(v2,rh(v2)*10));wh(v2,rh(v2)*10));print(rh(v2)));\n" +
					  "\tawait(cnt); print(rH(v3))\n" ;
		
		
		//new(v1,2);
		//newSemaphore(cnt,rH(v1),1);
		//fork(acquire(cnt);wh(v1,rh(v1)*10));print(rh(v1));); 
		//fork(acquire(cnt);wh(v1,rh(v1)*10));wh(v1,rh(v1)*2));print(rh(v1)););
		//acquire(cnt);
		//print(rh(v1)-1);

		
		Repo repo18 = new Repo("src/Logs/Repository/Problem18.txt");
		PrgState p18 = new PrgState();
		
		HeapAllocStmt ha151 = new HeapAllocStmt("v1", new ConstExp(2));
		NewSemaphStmt ns = new NewSemaphStmt("cnt", 
										  new HeapReadExp("v1"),
										  new ConstExp(1)
										 );
		CompStmt cs152 = new CompStmt(new AcquireStmt("cnt"),
									 new CompStmt(new HeapWriteStmt("v1",
											 						new ArithExp('*',
											 								     new HeapReadExp("v1"),
											 									 new ConstExp(10)
											 									)
											 						),
											 	new PrintStmt(new HeapReadExp("v1"))
											 	)
									);
		ForkStmt fs153 = new ForkStmt(cs152);
		
		CompStmt cs155 = new CompStmt(new AcquireStmt("cnt"),
									 new CompStmt(new HeapWriteStmt("v1",
											 						new ArithExp('*',
											 								     new HeapReadExp("v1"),
											 									 new ConstExp(10)
											 									)
											 						),
											 		new CompStmt(new HeapWriteStmt("v1",
									 												new ArithExp('*',
															 								     new HeapReadExp("v1"),
															 									 new ConstExp(2)
									 															)
											 										),
											 						new PrintStmt(new HeapReadExp("v1"))
											 				    )
											 		)
									);
		
		ForkStmt fs156 = new ForkStmt(cs155);
		AcquireStmt a157 = new AcquireStmt("cnt");
		PrintStmt p158	= new PrintStmt(new ArithExp ('-',
													 new HeapReadExp("v1"),
													 new ConstExp(1)
													 )
										);
		p18.addStatement(p158);
		p18.addStatement(a157);
		p18.addStatement(fs156);
		p18.addStatement(fs153);
		p18.addStatement(ns);
		p18.addStatement(ha151);
		repo18.addPrgState(p18);
		Controller c18 = new Controller(repo18);
		
		String ex18 = "\tnew(v1,2);\n" +
					 "\tNewSemaphoreStmt(cnt, rH(v1),1)\n" +
					 "\tfork(acquire(cnt);wh(v1,rh(v1)*10));print(rh(v1)););\n" +
					 "\tfork(acquire(cnt);wh(v1,rh(v1)*10));wh(v1,rh(v1)*2));print(rh(v1)););\n" +
					 "\tacquire(cnt);\n" +
					 "\tprint(rh(v1)-1);\n";
		
		TextMenu txtMenu = new TextMenu();
		txtMenu.addCommand(new ExitCommand("0", "\tExit\n"));
		txtMenu.addCommand(new RunExample("1", ex1, c1));
		txtMenu.addCommand(new RunExample("2", ex2, c2));
		txtMenu.addCommand(new RunExample("3", ex3, c3));
		txtMenu.addCommand(new RunExample("4", ex4, c4));
		txtMenu.addCommand(new RunExample("5", ex5, c5));
		txtMenu.addCommand(new RunExample("6", ex6, c6));
		txtMenu.addCommand(new RunExample("7", ex7, c7));
		txtMenu.addCommand(new RunExample("8", ex8, c8));
		txtMenu.addCommand(new RunExample("9", ex9, c9));
		txtMenu.addCommand(new RunExample("10", ex10, c10));
		txtMenu.addCommand(new RunExample("11", ex11, c11));
		txtMenu.addCommand(new RunExample("12", ex12, c12));
		txtMenu.addCommand(new RunExample("13", ex13, c13));
		txtMenu.addCommand(new RunExample("14", ex14, c14));
		txtMenu.addCommand(new RunExample("15", ex15, c15));
		txtMenu.addCommand(new RunExample("16", ex16, c16));
		txtMenu.addCommand(new RunExample("17", ex17, c17));
		txtMenu.addCommand(new RunExample("18", ex18, c18));
		return txtMenu;
	}
}
