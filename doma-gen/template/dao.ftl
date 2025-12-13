<#-- dao.ftl（DaoDesc 用 / フォーマット崩れ防止） -->

<#function convertDataType typeName>
    <#local t = typeName?replace("BigInteger", "Long")>
    <#return t>
</#function>

<#import "/lib.ftl" as lib>

<#if lib.copyright??>
    ${lib.copyright}
</#if>
<#if packageName??>package ${packageName};
</#if>

<#list importNames as importName>import ${importName};
</#list>
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

/**
* <#if entityDesc.comment?? && entityDesc.comment?length gt 0>${entityDesc.comment}<#else>${entityDesc.tableName!""}</#if>DAO
<#if lib.author??>
    * @author ${lib.author}
</#if>
*/
@Dao<#if configClassSimpleName??>(config = ${configClassSimpleName}.class)</#if>
@ConfigAutowireable
public interface ${simpleName} {

    /**
    * 登録します。
    * @param entity エンティティ
    * @return 登録された行数
    */
    @Insert
    int insert(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity);

    /**
    * 更新します。
    * @param entity エンティティ
    * @return 更新された行数
    */
    @Update
    int update(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity);

    /**
    * 削除します。
    * @param entity エンティティ
    * @return 削除された行数
    */
    @Delete
    int delete(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName}<#if entityDesc.entitySuffix??>${entityDesc.entitySuffix}</#if> entity);
}
