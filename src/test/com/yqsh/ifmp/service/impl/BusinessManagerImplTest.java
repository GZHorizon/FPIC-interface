package test.com.yqsh.ifmp.service.impl; 

import com.yqsh.ifmp.common.service.BaseIfmpService;
import com.yqsh.ifmp.service.impl.BusinessManagerImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/** 
* BusinessManagerImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 24, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BusinessManagerImplTest {

    private static final Logger log = Logger.getLogger(BusinessManagerImplTest.class);

    @Resource
    private BusinessManagerImpl BusinessManagerImpl;

    @Autowired
    @Qualifier("uuid")
    private String uuid;

    @Autowired
    @Qualifier("adminId")
    private String adminId;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: verify(String param) 
* 
*/ 
@Test
public void testVerify() throws Exception {
    BusinessManagerImpl.verify("hshh");
} 

/** 
* 
* Method: addAdmin(String param) 
* 
*/ 
@Test
public void testAddAdmin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: recharge(String param) 
* 
*/ 
@Test
public void testRecharge() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: search(String param) 
* 
*/ 
@Test
public void testSearch() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: lostCard(String param) 
* 
*/ 
@Test
public void testLostCard() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getDealDetail(String param) 
* 
*/ 
@Test
public void testGetDealDetail() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: queryDealDetail(String param) 
* 
*/ 
@Test
public void testQueryDealDetail() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getDoorDetail(String param) 
* 
*/ 
@Test
public void testGetDoorDetail() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAttDetail(String param) 
* 
*/ 
@Test
public void testGetAttDetail() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: synOrg(String param) 
* 
*/ 
@Test
public void testSynOrg() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: synCustomer(String param) 
* 
*/ 
@Test
public void testSynCustomer() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSubsidyDetail(String param) 
* 
*/ 
@Test
public void testGetSubsidyDetail() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAmmeterInfo(String param) 
* 
*/ 
@Test
public void testGetAmmeterInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCardInfo(String param) 
* 
*/ 
@Test
public void testGetCardInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deduction(String param) 
* 
*/ 
@Test
public void testDeduction() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getBatchCustmer(String param) 
* 
*/ 
@Test
public void testGetBatchCustmer() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: synDishes(String param) 
* 
*/ 
@Test
public void testSynDishes() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getDishesFlow(String param) 
* 
*/ 
@Test
public void testGetDishesFlow() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: validation(SearchDetailRequest ddr, boolean flag) 
* 
*/ 
@Test
public void testValidation() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("validation", SearchDetailRequest.class, boolean.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: createQuery(SearchDetailRequest ddr, String type) 
* 
*/ 
@Test
public void testCreateQuery() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("createQuery", SearchDetailRequest.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getTotalpage(int total, int pageSize) 
* 
*/ 
@Test
public void testGetTotalpage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("getTotalpage", int.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: dealSynOrg(SynOrgRequest synOrgRequest, BaseResponse response) 
* 
*/ 
@Test
public void testDealSynOrg() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("dealSynOrg", SynOrgRequest.class, BaseResponse.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: dealSynCustomer(SynCustomerRequest synCustomerRequest, BaseResponse response) 
* 
*/ 
@Test
public void testDealSynCustomer() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("dealSynCustomer", SynCustomerRequest.class, BaseResponse.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: validationAW(SearchAWRequest ddr, boolean flag) 
* 
*/ 
@Test
public void testValidationAW() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("validationAW", SearchAWRequest.class, boolean.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: dealSynDishes(SynDishesRequest req, BaseResponse response) 
* 
*/ 
@Test
public void testDealSynDishes() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("dealSynDishes", SynDishesRequest.class, BaseResponse.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: validationDish(DishesFlowQueryRequest ddr) 
* 
*/ 
@Test
public void testValidationDish() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BusinessManagerImpl.getClass().getMethod("validationDish", DishesFlowQueryRequest.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
