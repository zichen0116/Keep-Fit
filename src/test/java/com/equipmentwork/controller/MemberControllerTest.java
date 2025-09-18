package com.equipmentwork.controller;

import com.equipmentwork.entity.Member;
import com.equipmentwork.entity.MemberQueryParam;
import com.equipmentwork.entity.PageResult;
import com.equipmentwork.entity.Result;
import com.equipmentwork.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void page() {
        // Given
        MemberQueryParam param = new MemberQueryParam();
        param.setPage(1);
        param.setPageSize(10);
        param.setName("张三");
        param.setGender("男");
        param.setAge(25);

        PageResult<Member> pageResult = new PageResult<>();
        pageResult.setTotal(1);
        List<Member> members = Arrays.asList(new Member());
        pageResult.setRows(members);

        when(memberService.page(any(MemberQueryParam.class))).thenReturn(pageResult);

        // When & Then
        Result result = memberController.page(1, 10, "张三", "男", 25);

        // Verify
        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNotNull(result.getData());
        assertTrue(result.getData() instanceof PageResult);

        PageResult<Member> data = (PageResult<Member>) result.getData();
        assertEquals(1, data.getTotal());
        assertEquals(1, data.getRows().size());

        verify(memberService, times(1)).page(any(MemberQueryParam.class));
    }

    @Test
    void pageWithNullAge() {
        // Given
        MemberQueryParam param = new MemberQueryParam();
        param.setPage(1);
        param.setPageSize(10);
        param.setName("张三");
        param.setGender("男");
        param.setAge(0); // age为null时默认设为0

        PageResult<Member> pageResult = new PageResult<>();
        pageResult.setTotal(1);
        List<Member> members = Arrays.asList(new Member());
        pageResult.setRows(members);

        when(memberService.page(any(MemberQueryParam.class))).thenReturn(pageResult);

        // When - 传入null作为age参数
        Result result = memberController.page(1, 10, "张三", "男", null);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNotNull(result.getData());
        assertTrue(result.getData() instanceof PageResult);

        PageResult<Member> data = (PageResult<Member>) result.getData();
        assertEquals(1, data.getTotal());
        assertEquals(1, data.getRows().size());

        verify(memberService, times(1)).page(any(MemberQueryParam.class));
    }

    @Test
    void save() {
        // Given
        Member member = new Member();
        member.setName("张三");
        member.setGender("男");
        member.setAge(25);
        member.setPhone("13800138000");
        member.setPassword("123456");

        doNothing().when(memberService).insert(any(Member.class));

        // When
        Result result = memberController.save(member);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());

        verify(memberService, times(1)).insert(any(Member.class));
    }

    // @Test
    // void getById() {
    //     // Given
    //     int id = 1;
    //     Member member = new Member();
    //     member.setId(id);
    //     member.setName("张三");
    //     member.setGender("男");
    //     member.setAge(25);
    //
    //     when(memberService.getById(id)).thenReturn(member);
    //
    //     // When
    //     Result result = memberController.getById(id);
    //
    //     // Then
    //     assertNotNull(result);
    //     assertEquals(1, result.getCode());
    //     assertEquals("success", result.getMsg());
    //     assertNotNull(result.getData());
    //     assertTrue(result.getData() instanceof Member);
    //
    //     Member resultMember = (Member) result.getData();
    //     assertEquals(id, resultMember.getId());
    //     assertEquals("张三", resultMember.getName());
    //
    //     verify(memberService, times(1)).getById(id);
    // }

    @Test
    void update() {
        // Given
        Member member = new Member();
        member.setId(1);
        member.setName("张三");
        member.setGender("男");
        member.setAge(26);

        doNothing().when(memberService).update(any(Member.class));

        // When
        Result result = memberController.update(member);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());

        verify(memberService, times(1)).update(any(Member.class));
    }

    @Test
    void oneDelete() {
        // Given
        int id = 1;
        doNothing().when(memberService).delete(id);

        // When
        Result result = memberController.delete(id);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());

        verify(memberService, times(1)).delete(id);
    }

    @Test
    void list() {
        // Given
        List<Member> members = Arrays.asList(
                new Member(),
                new Member()
        );

        when(memberService.queryMember()).thenReturn(members);

        // When
        Result result = memberController.list();

        // Then
        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNotNull(result.getData());
        assertTrue(result.getData() instanceof List);
        assertEquals(2, ((List<?>) result.getData()).size());

        verify(memberService, times(1)).queryMember();
    }

    @Test
    void loginSuccess() {
        // Given
        String name = "张三";
        String password = "123456";
        Member member = new Member();
        member.setName(name);
        member.setPassword(password);

        when(memberService.login(name, password)).thenReturn(member);

        // When
        Result result = memberController.login(name, password);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("success", result.getMsg());
        assertNotNull(result.getData());
        assertTrue(result.getData() instanceof Member);

        Member resultMember = (Member) result.getData();
        assertEquals(name, resultMember.getName());

        verify(memberService, times(1)).login(name, password);
    }

    @Test
    void loginFailed() {
        // Given
        String name = "张三";
        String password = "wrong_password";

        when(memberService.login(name, password)).thenReturn(null);

        // When
        Result result = memberController.login(name, password);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getCode());
        assertEquals("登录失败", result.getMsg());
        assertNull(result.getData());

        verify(memberService, times(1)).login(name, password);
    }

    @Test
    void pageEndpoint() throws Exception {
        mockMvc.perform(get("/members")
                        .param("page", "1")
                        .param("pageSize", "10")
                        .param("name", "张三")
                        .param("gender", "男")
                        .param("age", "25")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));

        verify(memberService, times(1)).page(any(MemberQueryParam.class));
    }

    @Test
    void pageEndpointWithNullAge() throws Exception {
        mockMvc.perform(get("/members")
                        .param("page", "1")
                        .param("pageSize", "10")
                        .param("name", "张三")
                        .param("gender", "男")
                        // 不传递age参数，模拟age为null的情况
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));

        verify(memberService, times(1)).page(any(MemberQueryParam.class));
    }

    @Test
    void saveEndpoint() throws Exception {
        String memberJson = "{ \"name\": \"张三\", \"gender\": \"男\", \"age\": 25, \"phone\": \"13800138000\", \"password\": \"123456\" }";

        mockMvc.perform(post("/members")
                        .content(memberJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));

        verify(memberService, times(1)).insert(any(Member.class));
    }

    @Test
    void getByIdEndpoint() throws Exception {
        int id = 1;
        Member member = new Member();
        member.setId(id);
        member.setName("张三");

        when(memberService.getById(id)).thenReturn(member);

        mockMvc.perform(get("/members/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data.name").value("张三"));

        verify(memberService, times(1)).getById(id);
    }

    @Test
    void updateEndpoint() throws Exception {
        String memberJson = "{ \"id\": 1, \"name\": \"张三\", \"gender\": \"男\", \"age\": 26 }";

        mockMvc.perform(put("/members/update")
                        .content(memberJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));

        verify(memberService, times(1)).update(any(Member.class));
    }

    @Test
    void deleteEndpoint() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/members/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));

        verify(memberService, times(1)).delete(eq(id));
    }

    @Test
    void listEndpoint() throws Exception {
        List<Member> members = Arrays.asList(new Member(), new Member());
        when(memberService.queryMember()).thenReturn(members);

        mockMvc.perform(get("/members/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data").isArray());

        verify(memberService, times(1)).queryMember();
    }

    @Test
    void loginEndpoint() throws Exception {
        String name = "张三";
        String password = "123456";
        Member member = new Member();
        member.setName(name);

        when(memberService.login(name, password)).thenReturn(member);

        mockMvc.perform(post("/members/login")
                        .param("name", name)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data.name").value(name));

        verify(memberService, times(1)).login(name, password);
    }

    // 补充login异常测试
    @Test
    void testLoginException() throws Exception {
        // 模拟登录时发生异常的情况
        when(memberService.login(anyString(), anyString())).thenThrow(new RuntimeException("登录异常"));
        
        mockMvc.perform(post("/members/login")
            .param("name", "testUser")
            .param("password", "testPass"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.msg").value("登录异常"));
    }
}