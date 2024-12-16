## Project Structure  

### Chapter 1: Software Requirements Specification (SRS)  
1. **Problem Description**:  
   - Detailed explanation of the case study and its challenges.  

2. **Requirement Collection**:  
   - **Background Reading**: Summary of domain research with references.  
   - **Interviews**: Key insights from discussions with domain experts.  
   - **Questionnaires/Surveys**: Common issues identified and summarized responses.  
   - **Observation**: Real-world observations of processes in the domain.  

3. **Fact-Finding Chart**:  
   - Comprehensive table summarizing findings from all requirement collection methods.  

4. **Requirements List**:  
   - Consolidated list of system requirements.  

5. **User Privileges**:  
   - Defined user roles and associated privileges within the system.  

---

### Chapter 2: Database Design  
1. **Noun Analysis**:  
   - Identification of entities and relationships for ER Diagram design.  

2. **Schema and ER Diagram Design**:  
   - Initial ER Diagram and explanation of the schema.  

3. **ER Diagram Improvement**:  
   - Refinements made to entities, relationships, and the ER Diagram based on detailed analysis.  

4. **Mapping ER Model to Relational Model**:  
   - Conversion of the ER Diagram into a relational schema, with primary keys identified.  

5. **Create DDL Scripts**:  
   - Scripts implementing domain, key, referential integrity, and other constraints.  

---

### Chapter 3: Normalization of Database  
1. **Normalization and Schema Refinement**:  
   - Dependency analysis and identification of primary/foreign keys.  
   - Documentation of initial schemas and their refinement.  

2. **Redundancy and Anomalies Documentation**:  
   - Identified redundancies and anomalies in the initial design.  

3. **Normalization Process**:  
   - Enforced 1NF, eliminated partial dependencies for 2NF, and removed transitive dependencies to achieve 3NF/BCNF.  

---

### Chapter 4: Implementation of Database  
1. **Revised DDL Scripts**:  
   - Updated scripts reflecting the normalized database design.  

2. **Database Population**:  
   - Tables populated with 80–100 tuples each using **INSERT statements**.  

3. **SQL Queries**:  
   - Approximately 40 SQL queries ranging from simple to complex, including joins and sub-queries.  

---

### Chapter 5: Interface Implementation  
1. **Setup JDBC and Basic GUI**:  
   - Documentation of JDBC setup and a basic graphical user interface for database interaction.  

2. **CRUD Operations in GUI**:  
   - Implementation of **Create, Read, Update, and Delete** functionalities via the GUI.  

---

### Chapter 6: Technical Issues and Solutions  
1. **Technical Issues**:  
   - Comprehensive list of challenges faced during development, with their impact.  

2. **Solution**:  
   - Step-by-step documentation of approaches and tools used to resolve technical issues.  

---
