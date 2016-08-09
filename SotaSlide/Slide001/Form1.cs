using System;
using System.Drawing;
using System.Windows.Forms;
using System.Threading.Tasks;
using System.IO;
using System.Net;

namespace Slide001
{
    public partial class Form1 : Form
    {
        delegate void slideshowDelegate();
        int sleeptime = 34;
        string ip;
        static WebClient wc;
        static bool mouseDown = true;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.Show();
            sleeptime = initForm.interval;
            ip = initForm.ip;
            wc = new WebClient();
            backgroundWorker1.RunWorkerAsync();
        }

        public async void SlideShow()
        {
            Invoke(new slideshowDelegate(roadImage)); 
            // System.Threading.Thread.Sleep(sleeptime);
            await Task.Delay(sleeptime);
            
        }

        private void roadImage()
        {
            Image imtemp;
            Image imtemp2;
            try
            {
                imtemp = roadWebImage("http://" + ip + "/photoviewer/photo/abc.jpg");
                imtemp2 = roadWebImage("http://" + ip + "/photoviewer/photo/picture.jpg");
            }
            catch
            {
                imtemp = roadWebImage("yomikomityuu.png");
                imtemp2 = roadWebImage("yomikomityuu.png");
            }

            pictureBox1.Image = ResizeImage(imtemp);
            pictureBox3.Image = ResizeImage(imtemp2);
            Console.WriteLine("Read1");
            pictureBox1.Refresh();
            pictureBox3.Refresh();

            loadText();

        }

        private static Image roadWebImage(string url)
        {
            Stream stream = wc.OpenRead(url);
            Bitmap bitmap = new Bitmap(stream);
            stream.Close();
            wc.Dispose();

            return bitmap;
        }

        public static Image CreateImage(string filename)
        {
            System.IO.FileStream fs = new System.IO.FileStream(
                filename,
                System.IO.FileMode.Open,
                System.IO.FileAccess.Read);
            Image img = Image.FromStream(fs);
            fs.Close();
            return img;
        }

        public Image ResizeImage(Image img)
        {
            //描画先とするImageオブジェクトを作成する
            Bitmap canvas = new Bitmap(pictureBox1.Width, pictureBox1.Height);
            //ImageオブジェクトのGraphicsオブジェクトを作成する
            Graphics g = Graphics.FromImage(canvas);
            g.DrawImage(img, 0, 0, pictureBox1.Width, pictureBox1.Height);
            //Imageオブジェクトのリソースを解放する
            img.Dispose();
            //Graphicsオブジェクトのリソースを解放する
            g.Dispose();
            return canvas;
        }

        public void loadText()
        {
            
            try
            {
                wc.DownloadFile("http://" + ip + "/photoviewer/photo/OCtest.txt", "SotaText.txt");
                StreamReader sr = new StreamReader("SotaText.txt");
                label_status.Text = "顔認識情報 \n\n" + sr.ReadToEnd();
            }
            catch
            {
                Console.Write("顔情報 読み込みエラー");
            }
        }

        private void backgroundWorker1_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {
            while (true)
            {
                if (mouseDown) SlideShow();
            }
            //SlideShow();
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

        private void Form1_MouseDown(object sender, MouseEventArgs e)
        {
            mouseDown = false;
        }

        private void Form1_MouseUp(object sender, MouseEventArgs e)
        {
            mouseDown = true;
        }
    }
}
