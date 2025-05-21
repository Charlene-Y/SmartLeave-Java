# SmartLeave-Java
A Java-based leave application system simulating enterprise-level workflows with approval logic and user roles.

#### 專案簡介：
本專案為一套模擬企業內部員工請假與主管審核流程的管理系統，設計重點包括：
- 使用者角色與權限區分（員工、主管）
- 請假單狀態變更與歷史紀錄
- 基本 UI（Thymeleaf + Bootstrap）
- 錯誤驗證（如：請假日期不可早於今日）
- 示範完整後端開發流程及系統設計思維
- 
後端以 **Java Spring Boot** 實作，前端採用純 **HTML** 原型，並包含單元測試、整合測試與 Postman 驗證。

#### 使用技術： 
- **後端**：Java 17, Spring Boot, Spring Data JPA  
- **資料庫**：MySQL  
- **安全**：Spring Security  
- **測試**：JUnit 5, Mockito, SpringBootTest  
- **前端**：HTML, Bootstrap  
- **建置工具**：Maven  

#### 功能清單：
1. **使用者登入**：員工/主管 憑帳號密碼登入系統。  
2. **請假單申請**：員工可新增請假申請，系統驗證輸入合法性。  
3. **請假單查詢**：瀏覽本人的請假紀錄。  
4. **待審核列表**：主管登入後可看到所有待審核的請假申請。  
5. **審核操作**：主管可批准或駁回請假申請，狀態自動更新。  
6. **狀態管理**：請假單狀態含「審核中／已通過／駁回」。  
7. **流程邏輯**：基於角色決定可見與可執行操作。  
8. **錯誤驗證**：如請假日期不得早於當日、輸入欄位必填等。
    
---

### 系統開發進度  
> 此專案已完成 MVP 版本，並持續補上開發筆記與 Demo 資源。
