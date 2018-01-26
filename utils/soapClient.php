<?php
  // Para testar eh so descomentar os trechos de codigo(//)

  $url         = "http://localhost:8080/mini-projeto-biblioteca-master/WebLivraria?wsdl";
  $client     = new SoapClient($url, array("trace" => 1, "exception" => 0));

  # Create method
  // $params = array('autor'=>'João Victor','titulo'=>'TCC Agora vai', 'isbn'=>'111', 'editora'=>'ifpb', 'edicao'=>'v1');
   //$params = array('autor'=>'Rui Braz','titulo'=>'TCC Dessa vez acaba', 'isbn'=>'222', 'editora'=>'ifpb', 'edicao'=>'v1');
  //$params = array('autor'=>'Nicolas Goes','titulo'=>'TCC Finalmente', 'isbn'=>'333', 'editora'=>'ifpb', 'edicao'=>'v1');
 // $result = $client->__soapCall("create", array('parameters' => $params));

  # Delete method
   $params = array('isbn'=>'111');
   $result = $client->__soapCall("delete", array('parameters' => $params));
   var_dump($result);

  # Update method
 // $params = array('id'=>10,'titulo'=>'TCC ACABOU!!!');
 //  $result = $client->__soapCall("update", array('parameters' => $params));

  # ShowIsbn method
 //$params = array('isbn'=>'111');
 //   $result = $client->__soapCall("show", array('parameters' => $params));
 //  var_dump($result);

  # List method
  // $params = array();
  // $result = $client->__soapCall("list", array('parameters' => $params));
  // var_dump($result);
?>
