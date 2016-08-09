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
        int i = 0;
        int range = 0;
        string name = "photo";
        string ex = "jpg";
        int sleeptime = 3000;
        string ip;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.Show();
            sleeptime = initForm.interval;
            ip = initForm.ip;
            backgroundWorker1.RunWorkerAsync();
        }

        public void SlideShow()
        {
            if (i == range) i = 0;
            else i++;

            Invoke(new slideshowDelegate(roadImage));
            System.Threading.Thread.Sleep(sleeptime);
        }

        private void roadImage()
        {
            
            //Image imtemp = roadWebImage("http://" + ip + "/photoviewer/photo/abc.jpg");
            //pictureBox1.Image = ResizeImage(imtemp);

            Console.WriteLine("Read!!");
            pictureBox1.Refresh();


        }

        private static Image roadWebImage(string url)
        {
            WebClient wc = new WebClient();
            Stream stream = wc.OpenRead(url);
            Bitmap bitmap = new Bitmap(stream);
            stream.Close();
            wc.Dispose();

            return bitmap;
        }

        //public void loadText()
        //{
        //    StreamReader sr = new StreamReader("SotaText.txt");
        //    label2.Text = "Sota< \n\n" + sr.ReadToEnd();
        //}

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
            while(true) SlideShow();
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }
    }
}
