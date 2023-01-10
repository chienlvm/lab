package chienlvm.fsoft.vn.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chienlvm.fsoft.vn.utils.MyConstants;

@Controller
public class SimpleEmailExampleController {
	@Autowired
	public JavaMailSender emailSender;
	@ResponseBody
	@RequestMapping("/sendSimpleEmail")
	public String sendSimpleEmail() throws MessagingException {
//
//        // Create a Simple MailMessage.
//        SimpleMailMessage message = new SimpleMailMessage();
//        
//        message.setTo(MyConstants.FRIEND_EMAIL);
//        message.setSubject("Test Simple Email");
//        message.setText("Hello, Im testing Simple Email");
//        for(int i=0; i< 1000; i++) {
//            // Send Message!
//            this.emailSender.send(message);
//        }
//
//        return "Email Sent!";
		MimeMessage message = emailSender.createMimeMessage();
		message.setHeader("Content-Type", "text/plain; charset=UTF-8");
		boolean multipart = true;
		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
		String htmlMsg = "	<div marginheight=\"0\" marginwidth=\"0\">\r\n"
				+ "		<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\r\n"
				+ "			style=\"width:100%;margin:0 auto;background-color:#e6efff;max-width:680px\" width=\"100%\">\r\n"
				+ "			<tbody>\r\n"
				+ "				<tr>\r\n"
				+ "					<td align=\"center\" valign=\"top\" width=\"100%\">\r\n"
				+ "						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\r\n"
				+ "							style=\"margin:0 auto;max-width:600px;border:5px solid #e6efff;border-top:0\" width=\"100%\">\r\n"
				+ "							<tbody>\r\n"
				+ "								<tr>\r\n"
				+ "									<td align=\"center\" valign=\"top\" width=\"100%\">\r\n"
				+ "									</td>\r\n"
				+ "								</tr>\r\n"
				+ "								<tr>\r\n"
				+ "									<td align=\"center\" bgcolor=\"#ffffff\" valign=\"top\" width=\"100%\">\r\n"
				+ "										<table cellpadding=\"0\" cellspacing=\"0\" width=\"88%\">\r\n"
				+ "											<tbody>\r\n"
				+ "												<tr>\r\n"
				+ "													<td height=\"35\" style=\"height:35px\"> &nbsp;</td>\r\n"
				+ "												</tr>\r\n"
				+ "												<tr>\r\n"
				+ "													<td align=\"center\" valign=\"top\" width=\"100%\">\r\n"
				+ "														<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding:0\" width=\"100%\">\r\n"
				+ "															<tbody>\r\n"
				+ "																<tr>\r\n"
				+ "																	<td class=\"m_3335692465191621885welcome-message\" style=\"padding-bottom:27px\">\r\n"
				+ "																		<p\r\n"
				+ "																			style=\"font-family:Arial,sans-serif;font-size:16px;text-align:left;margin:0 0 0 0;color:#4a4a4a;line-height:21px\">\r\n"
				+ "																			<strong>Công việc tốt nhất dành cho bạn:</strong></p>\r\n"
				+ "																	</td>\r\n"
				+ "																</tr>\r\n"
				+ "															</tbody>\r\n"
				+ "														</table>\r\n"
				+ "														<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\"\r\n"
				+ "															style=\"border-top:1px solid #e5e5e5\" width=\"100%\">\r\n"
				+ "															<tbody>\r\n"
				+ "																<tr>\r\n"
				+ "																	<td align=\"center\" style=\"padding:20px 0 17px;border-bottom:1px solid #e5e5e5\" valign=\"top\" width=\"70\"> <a href=\"https://www.vietnamworks.com/frontend-developer-for-splunk-1588167-jv?utm_source=EmailTopJobs&amp;utm_medium=JobTitle&amp;utm_campaign=EmailTopJobs&amp;utm_content=SmartNaviIOPV2&amp;utm_term=\" style=\"outline:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.vietnamworks.com/frontend-developer-for-splunk-1588167-jv?utm_source%3DEmailTopJobs%26utm_medium%3DJobTitle%26utm_campaign%3DEmailTopJobs%26utm_content%3DSmartNaviIOPV2%26utm_term%3D&amp;source=gmail&amp;ust=1667574528800000&amp;usg=AOvVaw2q5_H5ergGMuV_q9d91_u6\">\r\n"
				+ "																			<img alt=\"\" src=\"https://ci3.googleusercontent.com/proxy/KUAWS-9Xuttmetq6CRBFesC5-FFh0IF9AKLvpz-BuPoTh2pbNb7VotqfnYrTWkMWeu3qw4uvm4VOtUnNYK-Vrn8n2igSIiJLqMfT=s0-d-e1-ft#https://images.vietnamworks.com/img/img-default-logo.svg\" style=\"border:0;padding-right:10px;margin:auto\" width=\"65\" class=\"CToWUd\" data-bit=\"iit\" jslog=\"138226; u014N:xr6bB; 53:W2ZhbHNlLDJd\"> </a></td>\r\n"
				+ "																	<td align=\"left\" style=\"padding:20px 0 17px;border-bottom:1px solid #e5e5e5\" valign=\"top\">\r\n"
				+ "																		<table cellpadding=\"0\" cellspacing=\"0\" class=\"m_3335692465191621885templateColumns\" style=\"border-collapse:collapse\" width=\"100%\">\r\n"
				+ "																			<tbody>\r\n"
				+ "																				<tr>\r\n"
				+ "																					<td class=\"m_3335692465191621885templateColumnContainer\" style=\"margin:0;padding:0\">\r\n"
				+ "																						<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"m_3335692465191621885templateColumnContainer\" style=\"border-collapse:collapse;table-layout:fixed\" width=\"100%\">\r\n"
				+ "																							<tbody>\r\n"
				+ "																								<tr>\r\n"
				+ "																									<td style=\"margin:0;padding:0 10px 0 0\"> <a class=\"m_3335692465191621885text-mobile16 m_3335692465191621885job\" href=\"https://www.vietnamworks.com/frontend-developer-for-splunk-1588167-jv?utm_source=EmailTopJobs&amp;utm_medium=JobTitle&amp;utm_campaign=EmailTopJobs&amp;utm_content=SmartNaviIOPV2&amp;utm_term=\" style=\"color:#005aff;text-decoration:none;font-size:18px;line-height:20px;font-weight:700;outline:none;font-family:Arial,sans-serif\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.vietnamworks.com/frontend-developer-for-splunk-1588167-jv?utm_source%3DEmailTopJobs%26utm_medium%3DJobTitle%26utm_campaign%3DEmailTopJobs%26utm_content%3DSmartNaviIOPV2%26utm_term%3D&amp;source=gmail&amp;ust=1667574528800000&amp;usg=AOvVaw2q5_H5ergGMuV_q9d91_u6\">\r\n"
				+ "																											Frontend Developer for Splunk</a>\r\n"
				+ "																										<p class=\"m_3335692465191621885text-mobile12\" style=\"margin:5px 0 0 0;padding:3px 0 0;line-height:18px;color:#777777;font-weight:700;font-size:13.5px;font-family:Arial,sans-serif\">\r\n"
				+ "																											Schiesser It, LLC</p>\r\n"
				+ "																										<p class=\"m_3335692465191621885text-mobile12\" style=\"margin:0 0 2px 0;padding:0;color:#999999;font-size:13.5px;font-family:Arial,sans-serif;line-height:18px\">\r\n"
				+ "																											<span style=\"width:auto\">Quốc tế </span></p>\r\n"
				+ "																									</td>\r\n"
				+ "																								</tr>\r\n"
				+ "																							</tbody>\r\n"
				+ "																						</table>\r\n"
				+ "																					</td>\r\n"
				+ "																				</tr>\r\n"
				+ "																			</tbody>\r\n"
				+ "																		</table>\r\n"
				+ "																	</td>\r\n"
				+ "																</tr>\r\n"
				+ "															</tbody>\r\n"
				+ "															</table>\r\n"
				+ "													</td>\r\n"
				+ "												</tr>\r\n"
				+ "											</tbody>\r\n"
				+ "										</table>\r\n"
				+ "									</td>\r\n"
				+ "								</tr>\r\n"
				+ "							</tbody>\r\n"
				+ "						</table>\r\n"
				+ "					</td>\r\n"
				+ "				</tr>\r\n"
				+ "			</tbody>\r\n"
				+ "		</table>\r\n"
				+ "		<p> &nbsp;</p>\r\n"
				+ "	</div>";
		message.setContent(htmlMsg, "text/html; charset=UTF-8");
		helper.setTo(MyConstants.FRIEND_EMAIL);
		helper.setSubject("Test send HTML email");
		this.emailSender.send(message);
		return "Email Sent!";
	}
}
