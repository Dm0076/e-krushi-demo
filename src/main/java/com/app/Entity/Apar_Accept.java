package com.app.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="Apar_Trn_Accept",schema = "ahistrn")
public class Apar_Accept {

	@Id
	@Column (name="num_apar_id")
	private Integer num_apar_id;
	
	@Column (name = "str_emp_no")
	private String str_emp_no;
	
	@Column(name ="str_agree_assessment")
	private String str_agree_assessment; 
	
	@Column (name ="str_disagree_remarks")
	private String str_disagree_remarks ;
	
	@Column (name ="num_overall_grad")
	private Integer num_overall_grad ;
	
	@Column (name ="str_palce")
	private String str_place ;
	
	@Column (name = "gnum_isvalid")
	private Integer gnum_isvalid ;
	
	@Column(name = "gnum_seat_id")
	private Integer gnum_seat_id;
	
	@Column (name = "dt_rep_date")
	private Date dt_rep_date;
	
   @Column (name = "gdt_entry")
   private Date gdt_entry;

public Integer getNum_apar_id() {
	return num_apar_id;
}

public void setNum_apar_id(Integer num_apar_id) {
	this.num_apar_id = num_apar_id;
}

public String getStr_emp_no() {
	return str_emp_no;
}

public void setStr_emp_no(String str_emp_no) {
	this.str_emp_no = str_emp_no;
}

public String getStr_agree_assessment() {
	return str_agree_assessment;
}

public void setStr_agree_assessment(String str_agree_assessment) {
	this.str_agree_assessment = str_agree_assessment;
}

public String getStr_disagree_remarks() {
	return str_disagree_remarks;
}

public void setStr_disagree_remarks(String str_disagree_remarks) {
	this.str_disagree_remarks = str_disagree_remarks;
}

public Integer getNum_overall_grad() {
	return num_overall_grad;
}

public void setNum_ovarall_grad(Integer num_ovarall_grad) {
	this.num_overall_grad = num_ovarall_grad;
}

public String getStr_place() {
	return str_place;
}

public void setStr_place(String str_place) {
	this.str_place = str_place;
}

public Integer getGnum_isvalid() {
	return gnum_isvalid;
}

public void setGnum_isvalid(Integer gnum_isvalid) {
	this.gnum_isvalid = gnum_isvalid;
}

public Integer getGnum_seat_id() {
	return gnum_seat_id;
}

public void setGnum_seat_id(Integer gnum_seat_id) {
	this.gnum_seat_id = gnum_seat_id;
}

public Date getDt_rep_date() {
	return dt_rep_date;
}

public void setDt_rep_date(Date dt_rep_date) {
	this.dt_rep_date = dt_rep_date;
}

public Date getGdt_entry() {
	return gdt_entry;
}

public void setGdt_entry(Date gdt_entry) {
	this.gdt_entry = gdt_entry;
}
   
   
   
}
