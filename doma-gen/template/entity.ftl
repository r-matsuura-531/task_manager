<#-- entity.ftl（EntityDesc 用 / フォーマット崩れ防止） -->

<#function convertDataType typeName>
    <#local t = typeName?replace("BigInteger", "Long")>
    <#return t>
</#function>

<#import "/lib.ftl" as lib>

<#-- ここで packageName を動的にしたい場合は uncomment
<#assign dirName = tableName?replace("_","")?replace("-","")?lower_case>
<#assign packageName = "com.example.taskmanager.infrastructure." + dirName>
-->

<#if lib.copyright??>
    ${lib.copyright}
</#if>
<#if packageName??>package ${packageName};
</#if>

<#list importNames as importName>import ${importName};
</#list>
import lombok.Getter;
import lombok.Setter;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.Id;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Table;
import org.seasar.doma.TableGenerator;
import org.seasar.doma.Version;

/**
* <#if showDbComment && comment?? && comment?length gt 0>${comment}<#else>${tableName}</#if>エンティティ
<#if lib.author??>
* @author ${lib.author}
</#if>
*/
@Entity<#if useListener || namingType != "NONE">(</#if><#if useListener>listener = ${listenerClassSimpleName}.class</#if><#if namingType != "NONE"><#if useListener>, </#if>naming = ${namingType.referenceName}</#if><#if useListener || namingType != "NONE">)</#if>
<#if (showCatalogName && catalogName??) || (showSchemaName && schemaName??) || (showTableName && tableName??)>
@Table(<#if showCatalogName && catalogName??>catalog = "${catalogName}"</#if><#if showSchemaName && schemaName??><#if showCatalogName && catalogName??>, </#if>schema = "${schemaName}"</#if><#if showTableName && tableName??><#if (showCatalogName && catalogName??) || (showSchemaName && schemaName??)>, </#if>name = "${tableName}"</#if>)
</#if>
@Getter
@Setter
public class <#if entityPrefix??>${entityPrefix}</#if>${simpleName}<#if entitySuffix??>${entitySuffix}</#if>Entity<#if superclassSimpleName??> extends ${superclassSimpleName}</#if> {

<#list ownEntityPropertyDescs as p>
    <#if showDbComment && p.comment?? && p.comment?length gt 0>
    /** ${p.comment} */
    <#else>
    /** ${p.columnName!""} */
    </#if>
    <#if p.id>
    @Id
        <#if p.generationType??>
    @GeneratedValue(strategy = ${p.generationType.referenceName})
            <#if p.generationType == "SEQUENCE">
    @SequenceGenerator(sequence = "${tableName}_${p.columnName}"<#if p.initialValue??>, initialValue = ${p.initialValue}</#if><#if p.allocationSize??>, allocationSize = ${p.allocationSize}</#if>)
            <#elseif p.generationType == "TABLE">
    @TableGenerator(pkColumnValue = "${tableName}_${p.columnName}"<#if p.initialValue??>, initialValue = ${p.initialValue}</#if><#if p.allocationSize??>, allocationSize = ${p.allocationSize}</#if>)
            </#if>
        </#if>
    </#if>
    <#if p.version>
    @Version
    </#if>
    <#if p.showColumnName && p.columnName??>
    @Column(name = "${p.columnName}")
    </#if>
    private ${convertDataType(p.propertyClassSimpleName)} ${p.name};

</#list>
<#if originalStatesPropertyName??>
    @OriginalStates
    <#if entityPrefix??>${entityPrefix}</#if>${simpleName}<#if entitySuffix??>${entitySuffix}</#if> ${originalStatesPropertyName};
</#if>
}
