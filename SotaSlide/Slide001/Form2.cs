using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Slide001
{
    public partial class Form2 : Form
    {
        delegate void slideshowDelegate();
        int i = 0;
        int range = 0;
        int sleeptime = 3000;
        string ip;
        static WebClient wc;

        public Form2()
        {
            InitializeComponent();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            this.Show();
            sleeptime = initForm.interval;
            ip = initForm.ip;
            wc = new WebClient();
            backgroundWorker1.RunWorkerAsync();
        }

        public async void SlideShow()
        {
            if (i == range) i = 0;
            else i++;

            Invoke(new slideshowDelegate(roadImage));
            // System.Threading.Thread.Sleep(sleeptime);
            await Task.Delay(sleeptime);
            
        }

        private void roadImage()
        {
            try {
                Image imtemp = roadWebImage("http://" + ip + "/photoviewer/photo/abc.jpg");
                pictureBox1.Image = ResizeImage(imtemp);
                
            }
            catch
            {
                Image imtemp = roadWebImage("yomikomityuu.png");
                pictureBox1.Image = ResizeImage(imtemp);
                pictureBox1.Refresh();
                Console.WriteLine("Read2_Error");
            }
            Console.WriteLine("Read2");
            pictureBox1.Refresh();
        }

        private static Image roadWebImage(string url)
        {
            Stream stream = wc.OpenRead(url);
            Bitmap bitmap = new Bitmap(stream);
            stream.Close();
            wc.Dispose();

            return bitmap;
        }

        public void loadText()
        {
            StreamReader sr = new StreamReader("SotaText.txt");
            try {
                label_status.Text = "顔認識情報 \n\n" + sr.ReadToEnd();
            }
            catch
            {
                Console.Write("顔情報 読み込みエラー");
            }
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

        private void backgroundWorker1_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {
            while (true) SlideShow();
            //SlideShow();
        }

        private void Form2_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }
    }
}
