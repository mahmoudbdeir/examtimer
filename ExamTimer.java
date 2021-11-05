/**
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
*
* @author  Mahmoud Bdeir
* @version 1.0
* @since   2020-12-18 5:25 AM UTC 
*/

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class ExamTimer extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamTimer frame = new ExamTimer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int examMinutes;
	int numberOfQuestions;
	int fixedSecondsPerQuestion;
	int secondsPerQuestion;
	int questionsAnswered;

	public ExamTimer() {
		JLabel questionsAnsweredLabel = new JLabel("0");
		setType(Type.UTILITY);
		setTitle("MHB Exam Timer");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 223);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("How many questions?");
		lblNewLabel.setBounds(10, 11, 128, 14);
		getContentPane().add(lblNewLabel);

		JSpinner questionsSpinner = new JSpinner();
		questionsSpinner.setBounds(10, 27, 100, 20);
		questionsSpinner.setValue(34);
		getContentPane().add(questionsSpinner);

		JLabel lblTimeInMinutes = new JLabel("Time in minutes:");
		lblTimeInMinutes.setBounds(10, 58, 128, 14);
		getContentPane().add(lblTimeInMinutes);

		JSpinner timeSpinner = new JSpinner();
		timeSpinner.setBounds(10, 75, 100, 20);
		timeSpinner.setValue(60);
		getContentPane().add(timeSpinner);

		JLabel lblNewLabel_1 = new JLabel("Question (sec)");
		lblNewLabel_1.setBounds(155, 11, 100, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel questionTime = new JLabel("0");
		questionTime.setHorizontalAlignment(SwingConstants.CENTER);
		questionTime.setFont(new Font("Tahoma", Font.PLAIN, 40));
		questionTime.setBounds(155, 27, 100, 36);
		getContentPane().add(questionTime);

		JLabel lblExamCountdown = new JLabel("Exam (min)");
		lblExamCountdown.setBounds(288, 11, 100, 14);
		getContentPane().add(lblExamCountdown);

		JLabel examTime = new JLabel("0");
		examTime.setHorizontalAlignment(SwingConstants.CENTER);
		examTime.setFont(new Font("Tahoma", Font.PLAIN, 40));
		examTime.setBounds(275, 27, 100, 36);
		getContentPane().add(examTime);

		JButton doneButton = new JButton("done with current question");
		doneButton.setEnabled(false);
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionsAnsweredLabel.setText("q" + ++questionsAnswered);
				secondsPerQuestion += fixedSecondsPerQuestion;
			}
		});
		doneButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		doneButton.setBounds(10, 106, 415, 64);
		getContentPane().add(doneButton);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeSpinner.setEnabled(false);
				questionsSpinner.setEnabled(false);
				examMinutes = Integer.parseInt(timeSpinner.getValue().toString());
				numberOfQuestions = Integer.parseInt(questionsSpinner.getValue().toString());
				fixedSecondsPerQuestion = (int) Math.round(60.0 * numberOfQuestions / examMinutes);
				secondsPerQuestion = fixedSecondsPerQuestion;
				examTime.setText("" + examMinutes);
				questionTime.setText("" + secondsPerQuestion);
				doneButton.setEnabled(true);
				startButton.setEnabled(false);
				TimerTask repeatedTask = new TimerTask() {
					public void run() {
						secondsPerQuestion -= 1;
						if (secondsPerQuestion % 60 == 0) {
							examMinutes -= 1;
						}
						if(secondsPerQuestion < 0) {
							questionTime.setForeground(Color.RED);
						} else if (secondsPerQuestion < fixedSecondsPerQuestion) {
							questionTime.setForeground(Color.BLACK);
						} else {
							questionTime.setForeground(Color.GREEN);
						}
						questionTime.setText("" + secondsPerQuestion);
						examTime.setText("" + examMinutes);
					}
				};
				Timer timer = new Timer("Timer");

				long delay = 1000L;
				long period = 1000L;
				timer.scheduleAtFixedRate(repeatedTask, delay, period);
			}
		});

		startButton.setBounds(335, 74, 90, 23);
		getContentPane().add(startButton);

		questionsAnsweredLabel.setBounds(155, 78, 46, 14);
		getContentPane().add(questionsAnsweredLabel);
	}
}
