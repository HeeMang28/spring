package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// �� �����̳ʿ� �־��ּ��� (��ϵ� ��Ʈ�ѷ��� �����մϴ�.)
@Controller
public class MVCController {

	// �⺻�ּ�(localhost:8181)�ڿ� /goA�� ���̸� goA() �޼��� ����
	@RequestMapping(value="/goA")
	public String goA() {
		System.out.println("goA �ּ� ���� ����");
		// ��� �������� views ���� �Ʒ��� A.jsp
		return "A";
	}
	
	// �ּ� /goB�� ���°� ���� ������ּ���.
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("ABCDEFGHIJKLNM");
		
		return "alphabet";
	}
	
	// �ּ� /goC�� �����ϴ� ��Ʈ�ѷ��� ������ּ���.
	// ��� �������� goC�̰�, ���ӽ� " ���ε� �׽�Ʈ" ��� ���ڿ��� �����ϼ���.
	
	@RequestMapping(value="/goC")
	// Model �� �������ָ� ���ε� �� ���������� .jsp���Ͽ� �����͸� �߼��� �� �ֽ��ϴ�. 
	public String goC(Model model) {
		// model.addAtrribute("���޸�", �ڷ�);
		// ���ε��ؼ� ���� �ڷ�� .jsp���Ͽ��� ${��Ī} ���� el�� ����� ����� �� �ֽ��ϴ�,
		// goC.jsp���� �Ʒ� ���ڿ��� body�±� ���� ���������.
		model.addAttribute("test", "goC���� ������ ���ڿ�");
		return "goC";
		
	}
	
	//goD�� �Ķ���͸� �Է¹��� �� �ֵ��� �غ��ڽ��ϴ�.
	@RequestMapping(value="/goD")
	// �ּ� �� ?dNum=�� ���·� ������ �ڷḦ ���� �� dNum������ �������ݴϴ�.
	// ���� �Ķ���͸� .jsp�� �����ֱ� ���ؼ��� ���� Model model�� �Ķ���Ϳ� �������ݴϴ�.
	public String goD(int dNum, Model model) {
		System.out.println("�ּҷ� ���޹��� �� : " + dNum);
		model.addAttribute("dNum", dNum);
		return "D";
		
	}
	
	// cToF �޼��带 ����ڽ��ϴ�.
	// ���� �µ��� �Է¹޾� ȭ�� �µ��� �ٲ㼭 ������ִ� ������ �ۼ����ּ���.
	// (ȭ�� - 32) / 1.8 = �����µ� �Դϴ�.
	// ���� �̸��� ctof.jsp�Դϴ�.
	
	@RequestMapping(value="/ctof", method=RequestMethod.POST)
	public String cToF(double c, Model model) {
		double f = c * 1.8 + 32;
		model.addAttribute("f", f);
		model.addAttribute("c", c);
		return "ctof";	
	}
	
	// ���� ���� ������ �Էµ� �µ��� �״�� �����µ��� ó���ϵ��� ����
	@RequestMapping(value="/ctof", method=RequestMethod.GET)
	public String cToFform() {
		return "ctofform";
	}
	
	// ���� ���� ������� bmi������������ �����ڽ��ϴ�.
	// ���������� ��� ������ �������� �����Ǹ� �����ּҴ� /bmi�� �����մϴ�.
	// bmi ������ ü�� / Ű(m) ^ 2 �� ������ ����Դϴ�.
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String bmi(@RequestParam("height") int cm, int kg, Model model) { 
		// requestparam ���� cm�� ���� ���̻� ���� ����.
		double m = cm / 100.0;
		double bmi = kg / (m * m);
		model.addAttribute("bmi", bmi);
		model.addAttribute("cm", cm);
		model.addAttribute("kg", kg);
		return "bmi";
	}
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String bmiForm() {
		return "bmiform";
	}
	
	// PathVariable�� �̿��ϸ� url���ϸ����ε� Ư�� �Ķ���͸� �޾ƿ� �� �ֽ��ϴ�.
	// reset������� url�� ó���Ҷ� �ַ� ����ϴ� ����Դϴ�.
	// /pathtest/���� �� ���� ��ġ�� �°��� page������ ������ ������ �����մϴ�.
	@RequestMapping(value="/pathtest/{page}")
	// int page ���ʿ� @PathVariable�� �ٿ��� ������
	public String pathTest(@PathVariable int page, Model model) {
		System.out.println(page);
		model.addAttribute("page", page);
		// �޾ƿ� page ������ path.jsp�� �����ּ���
		// path.jsp���� {path}������ ��ȸ���Դϴ� ��� ������ �߰� ���ּ���.
		return "path";
}
}