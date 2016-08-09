using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Slide001
{
    public partial class initForm : Form
    {
        Form1 f;
        //Form2 f2;

        public static int interval = 80;
        public static string ip;

        public initForm()
        {
            InitializeComponent();
        }

        private void initForm_Load(object sender, EventArgs e)
        {
            f = new Form1();
            //f2 = new Form2();
            textBox2.Text = interval.ToString();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            interval = int.Parse(textBox2.Text);
            ip = textBox_ip.Text;

            f.Show();
            //f2.Show();
            //f2.Location = new Point(f2.Location.X + 600, f2.Location.Y);
            this.Hide();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            DialogResult result = folderBrowserDialog1.ShowDialog();
            textBox_ip.Text = folderBrowserDialog1.SelectedPath.ToString();
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {
            int result;
            if (int.TryParse(textBox2.Text, out result)) textBox2.Text = result.ToString();
            else
            {
                MessageBox.Show("数値を記入してください");
                textBox2.Text = "";
            }
        }
        
    }
}
