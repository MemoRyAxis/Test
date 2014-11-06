package test.test.test.test.test;


public class MrHttp {
/*
	public static void main(String[] args) {

		 String url = "http://192.168.40.29:8080/smsService/sms/save4map";
		HttpClient httpClient = new HttpClient();

		LOGGER.info("\t\t\turl : " + url);

		Map<SendMesEntity, String> resultInfo = new HashMap<SendMesEntity, String>();
		List<String> resultList = new ArrayList<String>();
		int successCount = 0;

		// 遍历用户列表
		Iterator<SendMesEntity> iterator = sList.iterator();
		SendMesEntity msgPo;
		Map<String, String> msgInfo = new HashMap<String, String>();
		while (iterator.hasNext()) {
			PostMethod post = new PostMethod(url);
			// 拼接JSON
			msgPo = (SendMesEntity) iterator.next();
			StringBuffer contentSb = new StringBuffer("{\"MOBILECONTENT\":");
			msgInfo.put(msgPo.getSendTel(), msgPo.getSendCon());
			contentSb.append(JsonTools.map2json(msgInfo));
			contentSb.append(",\"SMSTYPEMAJOR\":1000,\"SMSTYPESUB\":1013}");
			LOGGER.info("\t\t\tjson content : " + contentSb.toString());

			post.addRequestHeader("Content-Type", "text/plain; charset=utf-8");
			post.setRequestBody(contentSb.toString());

			// http状态码
			int status;
			// 短信发送成功为1，失败为0
			String flag;
			// 请求返回内容
			String responseString;

			try {
				// 判断电话号码是否为手机号 如果不是便不发送请求
				if (SendMessage.isMobileNo(msgPo.getSendTel())) {
					status = httpClient.executeMethod(post);
				} else {
					status = 404;
				}
				LOGGER.info("\t\t\tstatus : " + status);
				if (status != 200) {
					responseString = "send fail";
					flag = "0";
				} else {
					responseString = JsonTools
							.jsonToMap(post.getResponseBodyAsString())
							.get("RESULT").toString();
					flag = "1";
					successCount++;
				}
				LOGGER.info("\t\t\tbat no : " + responseString);
				resultInfo.put(msgPo, flag);
				resultList.add(flag);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				status = 0;
				flag = "";
				msgInfo.clear();
				post.releaseConnection();
			}
		}

		return resultList;
	}
*/}
