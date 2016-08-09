//このソースは、VstoneMagicによって自動生成されました。
//ソースの内容を書き換えた場合、VstoneMagicで開けなくなる場合があります。
package	jp.co.mysota;
import	main.main.GlobalVariable;
import	jp.vstone.RobotLib.*;
import	jp.vstone.sotatalk.*;
import	jp.vstone.sotatalk.SpeechRecog.*;
import	java.awt.Color;
import	jp.vstone.camera.*;
import	java.io.*;
import	java.time.*;
import	jp.vstone.camera.FaceDetectLib.*;
import	java.util.Random;

public class mymain
{

	public CRobotPose pose;
	public String speechRecogResult;
	public String ocName;
	public String date_string;
	public String readText;
	public RecogResult recogresult;
	public LocalDateTime localDateTime;
	public String time_string;
	public int addFaceuserErrSayInterval;
	public String faceName;
	public String callName;
	public int faceDetectResultSmile;
	public int faceDetectResultAge;
	public void clearData()																								//@<BlockInfo>jp.vstone.block.func,32,240,320,240,False,3,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.robocam.setEnableFaceSearch(true);																//@<BlockInfo>jp.vstone.block.facedetect.detect,112,240,240,240,False,2,顔検出@</BlockInfo>
		GlobalVariable.robocam.setEnableSmileDetect(true);
		GlobalVariable.robocam.setEnableAgeSexDetect(true);

		GlobalVariable.robocam.StartFaceDetect();
		{
			GlobalVariable.detectCount=0;

																														//@<OutputChild>
			{																											//@<BlockInfo>jp.vstone.block.facedetect.user.clear,176,240,176,240,False,1,登録した顔のクリア(全消去)@</BlockInfo>
				for(int i=0;i<20;i++)
				{
					if(GlobalVariable.robocam.isAliveFaceDetectTask()) { GlobalVariable.robocam.clearUser(); break; }
					CRobotUtil.wait(100);
					if(i==10-1) CRobotUtil.Err("FaceUser", "顔検出が実行されていません。このブロックを使う際は、顔検出、顔追従ブロックに囲われた箇所で使用してください。");
				}
			}																											//@<EndOfBlock/>
																														//@</OutputChild>

		}
		GlobalVariable.robocam.StopFaceDetect();

																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	/*
	try{																												//@<BlockInfo>jp.vstone.block.text.read,1072,32,1072,32,False,6,リソースに登録したテキストファイルを読み込んで、変数String readTextに代入します@</BlockInfo>
		File file = new File("resource/体温.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));

		readText="";
		String read;
		while((read = br.readLine()) != null){
			readText += read;
		}

		br.close();
	}catch(FileNotFoundException e){
		System.out.println(e);
	}catch(IOException e){
		System.out.println(e);
	}
																														//@<EndOfBlock/>
	try{																												//@<BlockInfo>jp.vstone.block.freeproc,1136,32,1136,32,False,5,@</BlockInfo>
		File file = new File("resource/体温.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

		bw.write(date_string + time_string);
		bw.newLine();
		bw.write("まきのくん　38℃");
		bw.newLine();
		System.out.println("OK");
		bw.write("たかしくん　36℃");
		bw.newLine();
		System.out.println("OK");

		bw.close();
		}catch(FileNotFoundException e){
		System.out.println(e);
		}catch(IOException e){
		System.out.println(e);
		}
																														//@<EndOfBlock/>
	{																													//@<BlockInfo>jp.vstone.block.talk.tts,1200,32,1200,32,False,4,動作を伴わず音声合成のみ行います。@</BlockInfo>
		String file = TextToSpeechSota.getTTSFile((String)readText,(int)11,(int)13,(int)11);
		if(file!=null) CPlayWave.PlayWave(file,true);
	}
																														//@<EndOfBlock/>

	*/

	//@<Separate/>
	public void callName()																								//@<BlockInfo>jp.vstone.block.func,64,1344,1440,1344,False,21,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.robocam.setEnableFaceSearch(false);																//@<BlockInfo>jp.vstone.block.facedetect.traking,336,1344,1376,1344,False,20,tracking@</BlockInfo>
		GlobalVariable.robocam.setEnableSmileDetect(true);
		GlobalVariable.robocam.setEnableAgeSexDetect(true);

		GlobalVariable.robocam.StartFaceTraking();
		{
			GlobalVariable.detectCount=0;


																														//@<OutputChild>
			while(GlobalVariable.TRUE)																					//@<BlockInfo>jp.vstone.block.while.endless,400,1344,1312,1344,False,19,Endless@</BlockInfo>
			{

																														//@<OutputChild>
				GlobalVariable.faceresult = GlobalVariable.robocam.getDetectResult();									//@<BlockInfo>jp.vstone.block.facedetect.isdetect,464,1296,1248,1296,False,18,コメント@</BlockInfo>

				if(GlobalVariable.faceresult.isDetect()) GlobalVariable.detectCount++;
				else GlobalVariable.detectCount=0;

				if(GlobalVariable.detectCount>(int)2)
				{
																														//@<OutputChild>
					GlobalVariable.faceuser = GlobalVariable.robocam.getUser(GlobalVariable.faceresult);				//@<BlockInfo>jp.vstone.block.facedetect.user.get2,528,1248,1184,1248,False,16,認識した顔の特徴を取得して、グローバル変数FaceUser faceuserに代入します。また、登録済みのユーザの場合、名前をグローバル変数String facenameに代入します。@</BlockInfo>

					if(GlobalVariable.faceuser != null)
					{
						if(GlobalVariable.faceuser.getName() != null) GlobalVariable.facename = GlobalVariable.faceuser.getName();
						else GlobalVariable.facename="";
						
																														//@<OutputChild>
							if(!GlobalVariable.faceuser.isNewUser())														//@<BlockInfo>jp.vstone.block.facedetect.user.isknow,592,1200,1120,1200,False,14,グローバル変数FaceUser faceuserに記録された顔が登録された顔であるか@</BlockInfo>
							{
																															//@<OutputChild>
								callName=(String)GlobalVariable.facename;													//@<BlockInfo>jp.vstone.block.calculater,656,1200,656,1200,False,12,@</BlockInfo>
																															//@<EndOfBlock/>
								System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");											//@<BlockInfo>jp.vstone.block.freeproc,720,1200,720,1200,False,11,@</BlockInfo>
								System.out.println(callName + "を認識しました。");
								System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");
																															//@<EndOfBlock/>
								GlobalVariable.sotawish.Say((String)callName + "こんにちは。",MotionAsSotaWish.MOTION_TYPE_HELLO,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,784,1200,784,1200,False,10,@</BlockInfo>
																															//@<EndOfBlock/>
								GlobalVariable.sotawish.Say((String)"写真撮りまーす！",MotionAsSotaWish.MOTION_TYPE_CALL,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,848,1200,848,1200,False,9,@</BlockInfo>
																															//@<EndOfBlock/>
								takePhoto();																				//@<BlockInfo>jp.vstone.block.callfunc.base,992,1200,992,1200,False,8,@</BlockInfo>	@<EndOfBlock/>
								break;																						//@<BlockInfo>jp.vstone.block.break,1056,1200,1056,1200,False,7,break@</BlockInfo>	@<EndOfBlock/>
																															//@</OutputChild>
							
							}else
							{
																															//@<OutputChild>
								CPlayWave.PlayWave("resource/NG.wav",false);												//@<BlockInfo>jp.vstone.block.sound2,720,1296,720,1296,False,13,コメント@</BlockInfo>	@<EndOfBlock/>
																															//@</OutputChild>
							
							}
																															//@<EndOfBlock/>
																																//@</OutputChild>

					}
					else
					{
						
																														//@<OutputChild>
							CPlayWave.PlayWave("resource/NG.wav",false);													//@<BlockInfo>jp.vstone.block.sound2,656,1344,656,1344,False,15,コメント@</BlockInfo>	@<EndOfBlock/>
																																//@</OutputChild>

					}
																														//@<EndOfBlock/>
																														//@</OutputChild>

				}else
				{
																														//@<OutputChild>
					CPlayWave.PlayWave("resource/NG.wav",false);														//@<BlockInfo>jp.vstone.block.sound2,592,1392,592,1392,False,17,コメント@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>

				}
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>


		}
		GlobalVariable.robocam.StopFaceTraking();

																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public mymain()																										//@<BlockInfo>jp.vstone.block.func.constructor,32,32,992,32,False,35,@</BlockInfo>
	{
																														//@<OutputChild>
		/*CRobotPose pose*/;																							//@<BlockInfo>jp.vstone.block.variable,160,32,160,32,False,34,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String speechRecogResult*/;																					//@<BlockInfo>jp.vstone.block.variable,224,32,224,32,False,33,break@</BlockInfo>
																														//@<EndOfBlock/>
		ocName=null;																									//@<BlockInfo>jp.vstone.block.variable,288,32,288,32,False,32,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String date_string*/;																							//@<BlockInfo>jp.vstone.block.variable,352,32,352,32,False,31,break@</BlockInfo>
																														//@<EndOfBlock/>
		readText="";																									//@<BlockInfo>jp.vstone.block.variable,416,32,416,32,False,30,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*RecogResult recogresult*/;																					//@<BlockInfo>jp.vstone.block.variable,480,32,480,32,False,29,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*LocalDateTime localDateTime*/;																				//@<BlockInfo>jp.vstone.block.variable,544,32,544,32,False,28,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String time_string*/;																							//@<BlockInfo>jp.vstone.block.variable,608,32,608,32,False,27,break@</BlockInfo>
																														//@<EndOfBlock/>
		addFaceuserErrSayInterval=0;																					//@<BlockInfo>jp.vstone.block.variable,672,32,672,32,False,26,break@</BlockInfo>
																														//@<EndOfBlock/>
		faceName=null;																									//@<BlockInfo>jp.vstone.block.variable,736,32,736,32,False,25,break@</BlockInfo>
																														//@<EndOfBlock/>
		callName=null;																									//@<BlockInfo>jp.vstone.block.variable,800,32,800,32,False,24,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*int faceDetectResultSmile*/;																					//@<BlockInfo>jp.vstone.block.variable,864,32,864,32,False,23,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*int faceDetectResultAge*/;																					//@<BlockInfo>jp.vstone.block.variable,928,32,928,32,False,22,break@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void main()																									//@<BlockInfo>jp.vstone.block.func,32,144,1200,144,False,46,コメント@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.motion.ServoOn();																				//@<BlockInfo>jp.vstone.block.motoron,96,144,96,144,False,45,ON@</BlockInfo>	@<EndOfBlock/>
		time_string = CRobotUtil.getTimeString();																		//@<BlockInfo>jp.vstone.block.time.gettime,160,144,160,144,False,44,ロボット内のカレンダーより現在時刻を文字列で取得し、変数String time_stringに代入。@</BlockInfo>
																														//@<EndOfBlock/>
		date_string = CRobotUtil.getDateString();																		//@<BlockInfo>jp.vstone.block.time.getdate,224,144,224,144,False,43,ロボット内のカレンダーより現在年月日を文字列で取得し、変数String date_stringに代入。@</BlockInfo>
																														//@<EndOfBlock/>
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");																//@<BlockInfo>jp.vstone.block.freeproc,368,144,368,144,False,42,@</BlockInfo>
		System.out.println("日時と時刻を取得しました。");
		System.out.println("Sotaを起動します。");
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");

		try{
			File file = new File("resource/動作ログ.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write("[" + date_string + " " + time_string + "]" + "プログラムを実行しました。");
			bw.newLine();

			bw.close();
			}catch(FileNotFoundException e){
			System.out.println(e);
			}catch(IOException e){
			System.out.println(e);
			}
																														//@<EndOfBlock/>
		{																												//@<BlockInfo>jp.vstone.block.talk.tts,432,144,432,144,False,41,動作を伴わず音声合成のみ行います。@</BlockInfo>
			String file = TextToSpeechSota.getTTSFile((String)"テストはじめます。",(int)11,(int)13,(int)11);
			if(file!=null) CPlayWave.PlayWave(file,true);
		}
																														//@<EndOfBlock/>
		pose = new CRobotPose();																						//@<BlockInfo>jp.vstone.block.pose,512,144,512,144,False,40,コメント@</BlockInfo>
		pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
						new Short[]{7,-1222,-33,1191,90,-22,-60,-13}
						);
		pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
						new Short[]{100,100,100,100,100,100,100,100}
						);
		pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
						new Short[]{0,-255,0,0,80,80,0,80,80}
						);
		GlobalVariable.motion.play(pose,200);
		CRobotUtil.wait(200);																							//@<EndOfBlock/>
		openCampus();																									//@<BlockInfo>jp.vstone.block.callfunc.base,576,144,576,144,False,39,@</BlockInfo>	@<EndOfBlock/>
		time_string = CRobotUtil.getTimeString();																		//@<BlockInfo>jp.vstone.block.time.gettime,640,144,640,144,False,38,ロボット内のカレンダーより現在時刻を文字列で取得し、変数String time_stringに代入。@</BlockInfo>
																														//@<EndOfBlock/>
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");																//@<BlockInfo>jp.vstone.block.freeproc,704,144,704,144,False,37,@</BlockInfo>
		System.out.println("プログラムを終了します。");
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");

		try{
			File file = new File("resource/動作ログ.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write("[" + date_string + " " + time_string + "]" + "プログラムを終了しました。");
			bw.newLine();
			bw.newLine();

			bw.close();
			}catch(FileNotFoundException e){
			System.out.println(e);
			}catch(IOException e){
			System.out.println(e);
			}
																														//@<EndOfBlock/>
		pose = new CRobotPose();																						//@<BlockInfo>jp.vstone.block.pose,768,144,768,144,False,36,コメント@</BlockInfo>
		pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
						new Short[]{0,-900,0,900,0,0,0,0}
						);
		pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
						new Short[]{100,100,100,100,100,100,100,100}
						);
		pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
						new Short[]{0,-255,0,0,80,80,0,80,80}
						);
		GlobalVariable.motion.play(pose,200);
		CRobotUtil.wait(200);																							//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void takePhoto()																								//@<BlockInfo>jp.vstone.block.func,48,1504,480,1504,False,52,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		date_string = CRobotUtil.getDateString();																		//@<BlockInfo>jp.vstone.block.time.getdate,112,1504,112,1504,False,51,ロボット内のカレンダーより現在年月日を文字列で取得し、変数String date_stringに代入。@</BlockInfo>
																														//@<EndOfBlock/>
		time_string = CRobotUtil.getTimeString();																		//@<BlockInfo>jp.vstone.block.time.gettime,176,1504,176,1504,False,50,ロボット内のカレンダーより現在時刻を文字列で取得し、変数String time_stringに代入。@</BlockInfo>
																														//@<EndOfBlock/>
		{																												//@<BlockInfo>jp.vstone.block.facedetect.stillpicture,240,1504,240,1504,False,49,still@</BlockInfo>
			String filepath = "/var/sota/photo/";
			filepath += (String)callName+" "+date_string+" "+time_string;
			boolean isTrakcing=GlobalVariable.robocam.isAliveFaceDetectTask();
			if(isTrakcing) GlobalVariable.robocam.StopFaceTraking();
			GlobalVariable.robocam.initStill(new CameraCapture(CameraCapture.CAP_IMAGE_SIZE_5Mpixel, CameraCapture.CAP_FORMAT_MJPG));
			GlobalVariable.robocam.StillPicture(filepath);

			CRobotUtil.Log("stillpicture","save picthre file to \"" + filepath +"\"");
			if(isTrakcing) GlobalVariable.robocam.StartFaceTraking();
		}																												//@<EndOfBlock/>
		GlobalVariable.sotawish.Say((String)"いい写真が撮れました！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,304,1504,304,1504,False,48,@</BlockInfo>
																														//@<EndOfBlock/>
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");																//@<BlockInfo>jp.vstone.block.freeproc,416,1504,416,1504,False,47,@</BlockInfo>
		System.out.println(callName + "を撮影しました。");
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");

		try{
			File file = new File("resource/動作ログ.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			bw.write("[" + time_string + "]" + callName + " " + "の撮影を完了しました。");
			bw.newLine();
			bw.close();
			}catch(FileNotFoundException e){
			System.out.println(e);
			}catch(IOException e){
			System.out.println(e);
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void faceRegistration()																						//@<BlockInfo>jp.vstone.block.func,32,640,1440,592,False,74,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.robocam.setEnableFaceSearch(false);																//@<BlockInfo>jp.vstone.block.facedetect.traking,96,640,1376,592,False,73,tracking@</BlockInfo>
		GlobalVariable.robocam.setEnableSmileDetect(true);
		GlobalVariable.robocam.setEnableAgeSexDetect(true);

		GlobalVariable.robocam.StartFaceTraking();
		{
			GlobalVariable.detectCount=0;


																														//@<OutputChild>
			while(GlobalVariable.TRUE)																					//@<BlockInfo>jp.vstone.block.while.endless,160,640,1312,592,False,72,Endless@</BlockInfo>
			{

																														//@<OutputChild>
				GlobalVariable.faceresult = GlobalVariable.robocam.getDetectResult();									//@<BlockInfo>jp.vstone.block.facedetect.isdetect,224,592,1248,544,False,71,コメント@</BlockInfo>

				if(GlobalVariable.faceresult.isDetect()) GlobalVariable.detectCount++;
				else GlobalVariable.detectCount=0;

				if(GlobalVariable.detectCount>(int)2)
				{
																														//@<OutputChild>
					GlobalVariable.faceuser = GlobalVariable.robocam.getUser(GlobalVariable.faceresult);				//@<BlockInfo>jp.vstone.block.facedetect.user.get2,304,432,1184,496,False,66,認識した顔の特徴を取得して、グローバル変数FaceUser faceuserに代入します。また、登録済みのユーザの場合、名前をグローバル変数String facenameに代入します。@</BlockInfo>

					if(GlobalVariable.faceuser != null)
					{
						if(GlobalVariable.faceuser.getName() != null) GlobalVariable.facename = GlobalVariable.faceuser.getName();
						else GlobalVariable.facename="";
						
																														//@<OutputChild>
							if(!GlobalVariable.faceuser.isNewUser())														//@<BlockInfo>jp.vstone.block.facedetect.user.isknow,368,384,1088,384,False,64,グローバル変数FaceUser faceuserに記録された顔が登録された顔であるか@</BlockInfo>
							{
																															//@<OutputChild>
								callName=(String)GlobalVariable.facename;													//@<BlockInfo>jp.vstone.block.calculater,448,336,448,336,False,56,@</BlockInfo>
																															//@<EndOfBlock/>
								GlobalVariable.sotawish.Say((String)"君は" + callName + "だよね？",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,512,336,512,336,False,55,@</BlockInfo>
																															//@<EndOfBlock/>
											System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");											//@<BlockInfo>jp.vstone.block.freeproc,576,336,576,336,False,54,@</BlockInfo>
											System.out.println(callName + "は登録済みです。");
											System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");
							
											try{
												File file = new File("resource/動作ログ.txt");
												BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
							
												bw.write("[" + time_string + "]" + "既に登録されている" + " " + callName + " " + "を認識しました。");
												bw.newLine();
							
												bw.close();
												}catch(FileNotFoundException e){
												System.out.println(e);
												}catch(IOException e){
												System.out.println(e);
												}
																															//@<EndOfBlock/>
								break;																						//@<BlockInfo>jp.vstone.block.break,672,336,672,336,False,53,break@</BlockInfo>	@<EndOfBlock/>
																															//@</OutputChild>
							
							}else
							{
																															//@<OutputChild>
								GlobalVariable.sotawish.Say((String)"はじめまして！、名前を教えてくれない？",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,432,480,432,480,False,63,@</BlockInfo>
																															//@<EndOfBlock/>
								speechRecogResult = GlobalVariable.recog.getNamewithAbort((int)60000 , (int)3);				//@<BlockInfo>jp.vstone.block.talk.getname,496,432,1024,432,False,62,音声認識を行い、名前を取得する。取得結果はメンバー変数のspeechRecogResultに格納される。@</BlockInfo>
							
								if(speechRecogResult != null)
								{
																															//@<OutputChild>
									if(GlobalVariable.faceuser!=null)														//@<BlockInfo>jp.vstone.block.facedetect.user.add,576,400,832,400,False,61,@</BlockInfo>
									{
										GlobalVariable.faceuser.setName((String)speechRecogResult);
										int faceuserAddReturnCode = GlobalVariable.robocam.addUserwithErrorCode(GlobalVariable.faceuser);
										boolean isfaceuseradd = (faceuserAddReturnCode==1);
							
										String errMessageVoice=null;
										switch(faceuserAddReturnCode)
										{
											case -100: errMessageVoice="ユーザ情報がありません。";
											break;
											case -201: errMessageVoice="顔が上や下を向いているみたい。まっすぐ前を向いてください";
											break;
											case -202: errMessageVoice="首が傾いているみたい。まっすぐにしてください";
											break;
											case -203: errMessageVoice="顔が横を向いているみたい。まっすぐ前を見てください";
											break;
											case -300: errMessageVoice="もっと近くで顔を見せてください。";
											break;
											case -400: errMessageVoice="顔が良く見えませんでした。しっかり顔を見せてください。";
											break;
											case -500: errMessageVoice="顔が見つかりませんでした。";
											break;
										}
										if(errMessageVoice!=null && ++addFaceuserErrSayInterval == 3)
										{
											addFaceuserErrSayInterval=0;
											String file = TextToSpeechSota.getTTSFile(errMessageVoice);
											if(file!=null) CPlayWave.PlayWave(file,true);
										}
									 
										if(isfaceuseradd==true)
										{
																															//@<OutputChild>
										GlobalVariable.sotawish.Say((String)"君のことは覚えたよ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,640,400,640,400,False,59,@</BlockInfo>
																															//@<EndOfBlock/>
																			System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");									//@<BlockInfo>jp.vstone.block.freeproc,704,400,704,400,False,58,@</BlockInfo>
																			System.out.println("新しい人物を登録しました。");
																			System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-");
							
																			try{
																				File file = new File("resource/動作ログ.txt");
																				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
							
																				bw.write("[" + time_string + "]" + "新しい人物を登録しました。");
																				bw.newLine();
							
																				bw.close();
																				}catch(FileNotFoundException e){
																				System.out.println(e);
																				}catch(IOException e){
																				System.out.println(e);
																				}
																															//@<EndOfBlock/>
										break;																				//@<BlockInfo>jp.vstone.block.break,768,400,768,400,False,57,break@</BlockInfo>	@<EndOfBlock/>
																															//@</OutputChild>
							
										}else
										{
																															//@<OutputChild>
										GlobalVariable.sotawish.Say((String)"ごめん～！登録できなかったよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,640,496,640,496,False,60,@</BlockInfo>
																															//@<EndOfBlock/>
																															//@</OutputChild>
							
										}
							
									}
																															//@<EndOfBlock/>
																															//@</OutputChild>
							
								}else
								{
																															//@<OutputChild>
																															//@</OutputChild>
							
								}
																															//@<EndOfBlock/>
																															//@</OutputChild>
							
							}
																															//@<EndOfBlock/>
																																//@</OutputChild>

					}
					else
					{
						
																														//@<OutputChild>
							GlobalVariable.sotawish.Say((String)"おぼえられない～！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,384,608,384,608,False,65,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

					}
																														//@<EndOfBlock/>
																														//@</OutputChild>

				}else
				{
																														//@<OutputChild>
					switch(GlobalVariable.random.nextInt((int)3))														//@<BlockInfo>jp.vstone.block.random,320,688,480,688,False,70,@</BlockInfo>
					{
						case (int)0:
						{
																														//@<OutputChild>
							{																								//@<BlockInfo>jp.vstone.block.talk.tts,400,688,400,688,False,67,動作を伴わず音声合成のみ行います。@</BlockInfo>
								String file = TextToSpeechSota.getTTSFile((String)"もっとよく顔をみせて～！",(int)11,(int)13,(int)11);
								if(file!=null) CPlayWave.PlayWave(file,true);
							}
																															//@<EndOfBlock/>
																																//@</OutputChild>
							break;
						}
						case (int)1:
						{
																														//@<OutputChild>
							{																								//@<BlockInfo>jp.vstone.block.talk.tts,400,784,400,784,False,68,動作を伴わず音声合成のみ行います。@</BlockInfo>
								String file = TextToSpeechSota.getTTSFile((String)"あと少しで覚えられそうだ！",(int)11,(int)13,(int)11);
								if(file!=null) CPlayWave.PlayWave(file,true);
							}
																															//@<EndOfBlock/>
																																//@</OutputChild>
							break;
						}
						default:
						{
																														//@<OutputChild>
							{																								//@<BlockInfo>jp.vstone.block.talk.tts,400,880,400,880,False,69,動作を伴わず音声合成のみ行います。@</BlockInfo>
								String file = TextToSpeechSota.getTTSFile((String)"ふむふむふむ",(int)11,(int)13,(int)11);
								if(file!=null) CPlayWave.PlayWave(file,true);
							}
																															//@<EndOfBlock/>
																																//@</OutputChild>
							break;
						}

					}																									//@<EndOfBlock/>
																														//@</OutputChild>

				}
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>


		}
		GlobalVariable.robocam.StopFaceTraking();

																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	/*
	faceRegistration();																									//@<BlockInfo>jp.vstone.block.callfunc.base,704,224,704,224,False,75,@</BlockInfo>	@<EndOfBlock/>

	*/

	//@<Separate/>
	/*
	callName();																											//@<BlockInfo>jp.vstone.block.callfunc.base,592,224,592,224,False,76,@</BlockInfo>	@<EndOfBlock/>

	*/

	//@<Separate/>
	public void openCampus()																							//@<BlockInfo>jp.vstone.block.func,0,1824,1104,2032,False,105,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		int gender=0;																									//@<BlockInfo>jp.vstone.block.variable,112,1744,112,1744,False,104,break@</BlockInfo>
																														//@<EndOfBlock/>
		GlobalVariable.sotawish.Say((String)"こんにちは～！",MotionAsSotaWish.MOTION_TYPE_HELLO,(int)11,(int)13,(int)11);		//@<BlockInfo>jp.vstone.block.talk.say,240,1808,240,1808,False,103,@</BlockInfo>
																														//@<EndOfBlock/>
		ocName=(String)"お客";																							//@<BlockInfo>jp.vstone.block.calculater,384,1776,384,1776,False,102,@</BlockInfo>
																														//@<EndOfBlock/>
		GlobalVariable.robocam.setEnableFaceSearch(true);																//@<BlockInfo>jp.vstone.block.facedetect.traking,864,1744,784,2032,False,101,顔追従@</BlockInfo>
		GlobalVariable.robocam.setEnableSmileDetect(true);
		GlobalVariable.robocam.setEnableAgeSexDetect(true);

		GlobalVariable.robocam.StartFaceTraking();
		{
			GlobalVariable.detectCount=0;


																														//@<OutputChild>
			while(GlobalVariable.TRUE)																					//@<BlockInfo>jp.vstone.block.while.endless,928,1744,1424,1760,False,96,Endless@</BlockInfo>
			{

																														//@<OutputChild>
				GlobalVariable.faceresult = GlobalVariable.robocam.getDetectResult();									//@<BlockInfo>jp.vstone.block.facedetect.isdetect,992,1696,1360,1712,False,85,コメント@</BlockInfo>

				if(GlobalVariable.faceresult.isDetect()) GlobalVariable.detectCount++;
				else GlobalVariable.detectCount=0;

				if(GlobalVariable.detectCount>(int)5)
				{
																														//@<OutputChild>
					{																									//@<BlockInfo>jp.vstone.block.facedetect.gender2,1072,1520,848,1472,False,84,@</BlockInfo>
						boolean isMale=false,isFemale=false;
						GlobalVariable.faceresult = GlobalVariable.robocam.getDetectResult();
						if(GlobalVariable.faceresult.isMale()!=null) isMale = GlobalVariable.faceresult.isMale();
						if(GlobalVariable.faceresult.isFemale()!=null) isFemale = GlobalVariable.faceresult.isFemale();

						if( isMale )
						{
																														//@<OutputChild>
						GlobalVariable.sotawish.Say((String)ocName + "さんは、おとこのこだね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1136,1520,1136,1520,False,78,@</BlockInfo>
																														//@<EndOfBlock/>
						gender=0;																						//@<BlockInfo>jp.vstone.block.calculater,720,1520,720,1520,False,77,@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>

						}else if( isFemale )
						{
																														//@<OutputChild>
						GlobalVariable.sotawish.Say((String)ocName + "さんは、おんなのこだね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1136,1616,1136,1616,False,80,@</BlockInfo>
																														//@<EndOfBlock/>
						gender=1;																						//@<BlockInfo>jp.vstone.block.calculater,720,1600,720,1600,False,79,@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>

						}else
						{
																														//@<OutputChild>
						GlobalVariable.sotawish.Say((String)ocName + "さんは、男の子？女の子？",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1136,1712,1136,1712,False,82,@</BlockInfo>
																														//@<EndOfBlock/>
						gender=2;																						//@<BlockInfo>jp.vstone.block.calculater,720,1680,720,1680,False,81,@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>

						}
					}
																														//@<EndOfBlock/>
					break;																								//@<BlockInfo>jp.vstone.block.break,1280,1616,1280,1616,False,83,break@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>

				}else
				{
																														//@<OutputChild>
																														//@</OutputChild>

				}
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
			GlobalVariable.sotawish.Say((String)"スマイルくださ～い！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,16,2032,16,2032,False,109,@</BlockInfo>
																														//@<EndOfBlock/>
			while(GlobalVariable.TRUE)																					//@<BlockInfo>jp.vstone.block.while.endless,80,2032,336,2032,False,95,Endless@</BlockInfo>
			{

																														//@<OutputChild>
				GlobalVariable.faceresult = GlobalVariable.robocam.getDetectResult();									//@<BlockInfo>jp.vstone.block.facedetect.isdetect,144,1984,272,1984,False,88,コメント@</BlockInfo>

				if(GlobalVariable.faceresult.isDetect()) GlobalVariable.detectCount++;
				else GlobalVariable.detectCount=0;

				if(GlobalVariable.detectCount>(int)2)
				{
																														//@<OutputChild>
					GlobalVariable.faceresult = GlobalVariable.robocam.getDetectResult();								//@<BlockInfo>jp.vstone.block.facedetect.age.get2,256,1888,256,1888,False,87,現在認識している顔の年齢がfaceDetectResultAgeに格納される。顔が認識されない場合、faceDetectResultAgeに-1が代入される。@</BlockInfo>
					faceDetectResultAge = GlobalVariable.faceresult.getAge();											//@<EndOfBlock/>
					break;																								//@<BlockInfo>jp.vstone.block.break,208,1984,208,1984,False,86,break@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>

				}else
				{
																														//@<OutputChild>
																														//@</OutputChild>

				}
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
			GlobalVariable.faceresult = GlobalVariable.robocam.getDetectResult();										//@<BlockInfo>jp.vstone.block.facedetect.smile.get2,400,2032,400,2032,False,94,最後に認識された顔の年齢がfaceDetectResultSmileに格納される。@</BlockInfo>
			faceDetectResultSmile = GlobalVariable.faceresult.getSmile();												//@<EndOfBlock/>
			{																											//@<BlockInfo>jp.vstone.block.talk.tts,464,2032,464,2032,False,93,動作を伴わず音声合成のみ行います。@</BlockInfo>
				String file = TextToSpeechSota.getTTSFile((String)"あなたの笑顔の点数は～",(int)11,(int)13,(int)11);
				if(file!=null) CPlayWave.PlayWave(file,true);
			}
																														//@<EndOfBlock/>
			GlobalVariable.sotawish.Say((String)String.valueOf(faceDetectResultSmile) + "点でした～！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,528,2032,528,2032,False,92,@</BlockInfo>
																														//@<EndOfBlock/>
			if(faceDetectResultSmile>60)																				//@<BlockInfo>jp.vstone.block.if,592,1984,720,1984,False,91,コメント@</BlockInfo>
			{
																														//@<OutputChild>
				GlobalVariable.sotawish.Say((String)"素敵な笑顔～！ありがとう～！",MotionAsSotaWish.MOTION_TYPE_CALL,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,1984,656,1984,False,89,@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
			else
			{
																														//@<OutputChild>
				GlobalVariable.sotawish.Say((String)"いい笑顔だね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,2080,656,2080,False,90,@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>


		}
		GlobalVariable.robocam.StopFaceTraking();

																														//@<EndOfBlock/>
		GlobalVariable.sotawish.Say((String)"最後に写真撮りまーす！こっちをみて～！3 2 1",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,848,2032,848,2032,False,100,@</BlockInfo>
																														//@<EndOfBlock/>
		GlobalVariable.robocam.initStill(new CameraCapture(CameraCapture.CAP_IMAGE_SIZE_QVGA, CameraCapture.CAP_FORMAT_MJPG));	//@<BlockInfo>jp.vstone.block.freeproc,896,2112,896,2112,False,99,@</BlockInfo>
		String filepath = "/var/sota/photo/";
		filepath += (String)"picture";
		boolean isTrakcing=GlobalVariable.robocam.isAliveFaceDetectTask();
		if(isTrakcing) GlobalVariable.robocam.StopFaceTraking();
		GlobalVariable.robocam.StillPicture(filepath);
		CRobotUtil.Log("stillpicture","save picthre file to \"" + filepath +"\"");
		if(isTrakcing) GlobalVariable.robocam.StartFaceTraking();
																														//@<EndOfBlock/>
		GlobalVariable.sotawish.Say((String)"写真撮れました！ありがとう～！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,976,2032,976,2032,False,98,@</BlockInfo>
																														//@<EndOfBlock/>
		try{																											//@<BlockInfo>jp.vstone.block.freeproc,1040,2032,1040,2032,False,97,@</BlockInfo>
			File file = new File("/var/sota/photo/OCtest.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write("なまえ:お客さん");
			bw.newLine();
			if(gender == 0){
			bw.write("せいべつ:男");
			}else if(gender == 1){
			bw.write("せいべつ:女");
			}else{
			bw.write("せいべつ:わかりませんでした");
			}
			bw.newLine();
			bw.write("えがお:" + String.valueOf(faceDetectResultSmile));
			bw.newLine();
			bw.write("ねんれい:" + String.valueOf(faceDetectResultAge));
			bw.close();

			}catch(FileNotFoundException e){
			System.out.println(e);
			}catch(IOException e){
			System.out.println(e);
			}
		try{
			File file = new File("/var/sota/photo/OCtest2.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			bw.write("なまえ:お客さん");
			bw.newLine();
			if(gender == 0){
			bw.write("せいべつ:男");
			}else if(gender == 1){
			bw.write("せいべつ:女");
			}else{
			bw.write("せいべつ:わかりませんでした");
			}
			bw.newLine();
			bw.write("えがお:" + String.valueOf(faceDetectResultSmile));
			bw.newLine();
			bw.write("ねんれい:" + String.valueOf(faceDetectResultAge));
			bw.newLine();
			bw.close();

			}catch(FileNotFoundException e){
			System.out.println(e);
			}catch(IOException e){
			System.out.println(e);
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	/*
	{																													//@<BlockInfo>jp.vstone.block.facedetect.stillpicture,912,1952,912,1952,False,106,still@</BlockInfo>
		String filepath = "/var/sota/photo/";
		filepath += (String)"picture";
		boolean isTrakcing=GlobalVariable.robocam.isAliveFaceDetectTask();
		if(isTrakcing) GlobalVariable.robocam.StopFaceTraking();
		GlobalVariable.robocam.initStill(new CameraCapture(CameraCapture.CAP_IMAGE_SIZE_5Mpixel, CameraCapture.CAP_FORMAT_MJPG));
		GlobalVariable.robocam.StillPicture(filepath);

		CRobotUtil.Log("stillpicture","save picthre file to \"" + filepath +"\"");
		if(isTrakcing) GlobalVariable.robocam.StartFaceTraking();
	}																													//@<EndOfBlock/>

	*/

	//@<Separate/>
	public void likeVideo()																								//@<BlockInfo>jp.vstone.block.func,0,2192,624,2192,False,108,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.robocam.initStill(new CameraCapture(CameraCapture.CAP_IMAGE_SIZE_QVGA, CameraCapture.CAP_FORMAT_MJPG));	//@<BlockInfo>jp.vstone.block.freeproc,64,2192,64,2192,False,107,@</BlockInfo>
				while(true){
							{
								String filepath = "/var/sota/photo/";
								filepath += (String)"abc";
								boolean isTrakcing=GlobalVariable.robocam.isAliveFaceDetectTask();
								if(isTrakcing) GlobalVariable.robocam.StopFaceTraking();
								GlobalVariable.robocam.StillPicture(filepath);
								CRobotUtil.Log("stillpicture","save picthre file to \"" + filepath +"\"");
								if(isTrakcing) GlobalVariable.robocam.StartFaceTraking();
							}
						}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

}
