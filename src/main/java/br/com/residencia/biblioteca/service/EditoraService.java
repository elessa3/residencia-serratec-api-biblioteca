package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivrosDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livros;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivrosRepository;


@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	LivrosRepository livrosRepository;
	
	@Autowired
	LivrosService livrosService;
	
	@Autowired
	EmailService emailService;
	
	@Value("${imgbb.host.url}")
	private String imgBBHostUrl;
	
	@Value("${imgbb.host.key}")
    private String imgBBHostKey;
	
	
	public List<Editora> getAllEditora() {
		return editoraRepository.findAll();
	}	
	
	public List<EditoraDTO> getAllEditoraDTO() {
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		

		//1. Percorrer a lista de entidades Editora (chamada listaEditora)
		//2. Na lista de entidade, pegar cada entidade existente nela
		for(Editora editora : listaEditora) {

			//3. Transformar cada entidade existente na lista em um DTO
			EditoraDTO editoraDTO = toDTO(editora);
			
			//OBS: para converter a entidade no DTO, usar o metodo toDTO			
			//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
			listaEditoraDTO.add(editoraDTO);
		}		
		
		//5. Retornar/devolver a lista de DTO preenchida
		return listaEditoraDTO;					
	}
	
	public Editora getEditoraById(Integer id) {		
		return editoraRepository.findById(id).orElse(null);
	}
	
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}	
		
	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = toEntidade(editoraDTO);			
		Editora novaEditora = editoraRepository.save(editora);
		
		EditoraDTO editoraAtualizadaDTO = toDTO(novaEditora); 
		return editoraAtualizadaDTO;			
	}
	
	//*******************REVER********************
	
	public Editora saveEditoraFromApi(String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = consultaCnpjApiExterna(cnpj);
				
		Editora editora = new Editora();
		editora.setNome(consultaCnpjDTO.getNome());				
		
		return editoraRepository.save(editora);
	}
	
	//*********************************************
	
	public Editora updateEditora(Editora editora, Integer id) {
		
		Editora editoraExistenteNoBanco = getEditoraById(id);		
		editoraExistenteNoBanco.setNome(editora.getNome());			
		return editoraRepository.save(editoraExistenteNoBanco);
	}
	
	private Editora toEntidade(EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		
		editora.setNome(editoraDTO.getNome());
		return editora;
	}
	
	public EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
				
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());	
		//editoraDTO.setImagemFileName(editora.getImagemFileName());
		//editoraDTO.setImagemNome(editora.getImagemNome());
		//editoraDTO.setImagemUrl(editora.getImagemUrl());
		return editoraDTO;
	}
	
	public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, Integer id) {
		Editora editoraExistenteNoBanco = getEditoraById(id);
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		
		if(editoraExistenteNoBanco != null) {
			//editoraDTO.setCodigoEditora(editoraExistenteNoBanco.getCodigoEditora());
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			editoraAtualizadaDTO = toDTO(editoraAtualizada);			
		}
		
		//emailService.sendEmail("ericabombom3@hotmail.com", "Teste de envio de email", "Testando o envio no corpo do email");
		return editoraAtualizadaDTO;
	}
	
	//*********************************REVER
	
	public ConsultaCnpjDTO consultaCnpjApiExterna(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);
		
		ConsultaCnpjDTO consultaCnpjDTO = restTemplate.getForObject(uri, ConsultaCnpjDTO.class, params);
		
		return consultaCnpjDTO;
	}
	
	//****************************************	
	
	public Editora deleteEditora(Integer id) {		
		editoraRepository.deleteById(id);		
		return getEditoraById(id);
	}
	
	//***************************
	public List<EditoraDTO> getAllEditorasLivrosDTO() {
		
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();		
	
		
		for(Editora editora : listaEditora) {			
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livros> listaLivros = new ArrayList<>();
			List<LivrosDTO> listaLivrosDTO = new ArrayList<>();
						
			listaLivros = livrosRepository.findByEditora(editora);
			
			for(Livros livros : listaLivros) {
				LivrosDTO livrosDTO = livrosService.toDTO(livros);
				listaLivrosDTO.add(livrosDTO);
			}			
			
			editoraDTO.setListaLivrosDTO(listaLivrosDTO);	
			
			listaEditoraDTO.add(editoraDTO);
		}			
		return listaEditoraDTO;				
	}
	
	//******************************************************
	
	/*
	public ResponseEntity<String> saveFotoImgBB(String editora,
			MultipartFile file) throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		String serverUrl = imgBBHostUrl + imgBBHostKey;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		// This nested HttpEntiy is important to create the correct
		// Content-Disposition entry with metadata "name" and "filename"
		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		
		ContentDisposition contentDisposition = ContentDisposition
				.builder("form-data")
				.name("image")
				.filename(file.getOriginalFilename())
				.build();
		
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		
		HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("image", fileEntity);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity =
				new HttpEntity<>(body, headers);
		
		
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(
					serverUrl,
					HttpMethod.POST,
					requestEntity,
					String.class);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public ResponseEntity<String> saveEditoraComFotoNew(String editora,
			MultipartFile file) throws IOException {
        
		RestTemplate restTemplate = new RestTemplate();
		//String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // This nested HttpEntiy is important to create the correct
        // Content-Disposition entry with metadata "name" and "filename"
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("source")
                .filename(file.getOriginalFilename())
                .build();
        
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("source", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);
        
        
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(
            		serverUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        
        return response;
    }
	
	public ResponseEntity<String> saveEditoraComFoto(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) throws IOException {
		
		CloseableHttpClient httpClient		
	      = HttpClients.custom()
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    
		HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    
		requestFactory.setHttpClient(httpClient);
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

	    LinkedMultiValueMap<String, String> pdfHeaderMap = new LinkedMultiValueMap<>();
	    pdfHeaderMap.add("Content-disposition", "form-data; name=source; filename=" + file.getOriginalFilename());
	    pdfHeaderMap.add("Content-type", "image/jpeg");
	    HttpEntity<byte[]> doc = new HttpEntity<byte[]>(file.getBytes(), pdfHeaderMap);

	    LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
	    multipartReqMap.add("source", doc);

	    HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);
	    
	    //ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, reqEntity, String.class);		
		
		ResponseEntity<String> response = restTemplate
				.getForEntity(serverUrl, String.class, reqEntity);
	    
		return response;		
	}
	
	public ResponseEntity<String> saveEditoraComFotoDesisti(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) {
		
		CloseableHttpClient httpClient
	      = HttpClients.custom()
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    
		HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    
		requestFactory.setHttpClient(httpClient);		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		try {
			//byte[] image = Base64.encodeBase64(file.getBytes());
	        //String result = new String(image);
	        //System.out.println("Base64: " + result);
			//body.add("source", result);			
	        
			body.add("source", new ByteArrayResource(file.getBytes()));

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao obter os dados da imagem. - " + e);
		}
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpEntity<MultiValueMap<String, Object>> 
		requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<String> response = restTemplate
				.getForEntity(serverUrl, String.class, requestEntity);
		
		return response;		
	}
	
	public ResponseEntity<String> saveEditoraComFotoQuaseOK(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) {
		
		//Editora editoraFromJson = convertEditoraFromStringJson(editora);
		//Editora novaEditora= editoraRepository.save(editoraFromJson);
		
		CloseableHttpClient httpClient
	      = HttpClients.custom()
	    	.setRedirectStrategy(new LaxRedirectStrategy())
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    
		HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    
		requestFactory.setHttpClient(httpClient);		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		try {
			//byte[] fileContent = FileUtils.readFileToByteArray(new File(file));
			//String encodedString = Base64.getEncoder().encodeToString(fileContent);
			
			byte[] image = Base64.encodeBase64(file.getBytes());
	        String result = new String(image);
			
			
	        //body.add("source", new ByteArrayResource(file.getBytes()));
			body.add("source", result);
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao obter os dados da imagem. - " + e);
		}
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpEntity<MultiValueMap<String, Object>> 
		requestEntity = new HttpEntity<>(body, headers);
		/*
		ResponseEntity<FreeImageHostDTO> response = restTemplate
		  .postForEntity(serverUrl, requestEntity, FreeImageHostDTO.class);
		 */		
		
	/*
	ResponseEntity<String> response = restTemplate
				.postForEntity(serverUrl, requestEntity, String.class);
		
		return response;		
	}
	
	public ResponseEntity<String> saveEditoraComFotoOK(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) throws IOException {
		
		CloseableHttpClient httpClient
	      = HttpClients.custom()
	    	.setRedirectStrategy(new LaxRedirectStrategy())
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    
		HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    
		requestFactory.setHttpClient(httpClient);

	    String urlOverHttps
	      = freeImageHostUrl + freeImageHostKey;
	    
		//RestTemplate restTemplate = new RestTemplate();
		//restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		//String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		LinkedMultiValueMap<String, String> pdfHeaderMap = new LinkedMultiValueMap<>();
		pdfHeaderMap.add("Content-disposition", "form-data; name=source; filename=" + file.getOriginalFilename());
		pdfHeaderMap.add("Content-type", "image/jpeg");
		HttpEntity<byte[]> doc = new HttpEntity<byte[]>(file.getBytes(), pdfHeaderMap);
		
		LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
		multipartReqMap.add("source", doc);
		
		HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);

	    ResponseEntity<String> response 
	      = new RestTemplate(requestFactory).exchange(
	      urlOverHttps, HttpMethod.POST, reqEntity, String.class);
	    
		
		//ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, reqEntity, String.class);		
		
		return response;		
	}
	
	public ResponseEntity<String> saveEditoraComFotoOld2(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);
		
		//restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

	    LinkedMultiValueMap<String, String> pdfHeaderMap = new LinkedMultiValueMap<>();
	    pdfHeaderMap.add("Content-disposition", "form-data; name=source; filename=" + file.getOriginalFilename());
	    pdfHeaderMap.add("Content-type", "image/jpeg");
	    HttpEntity<byte[]> doc = new HttpEntity<byte[]>(file.getBytes(), pdfHeaderMap);

	    LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
	    multipartReqMap.add("source", doc);

	    HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);
	    ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, reqEntity, String.class);		
		
		return response;		
	}
	
	public ResponseEntity<String> saveEditoraComFotoOld(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) {
		
		Editora editoraFromJson = convertEditoraFromStringJson(editora);
		//Editora novaEditora= editoraRepository.save(editoraFromJson);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		try {
			body.add("source", new ByteArrayResource(file.getBytes()));
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao obter os dados da imagem. - " + e);
		}
		
		RestTemplate restTemplate = new RestTemplate();
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpEntity<MultiValueMap<String, Object>> 
			requestEntity = new HttpEntity<>(body, headers);
/*
		ResponseEntity<FreeImageHostDTO> response = restTemplate
		  .postForEntity(serverUrl, requestEntity, FreeImageHostDTO.class);
*/		
	
	/*
		ResponseEntity<String> response = restTemplate
				  .postForEntity(serverUrl, requestEntity, String.class);

		return response;		
	}
	
	private Editora convertEditoraFromStringJson(String editoraJson) {
		Editora editora = new Editora();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			editora = objectMapper.readValue(editoraJson, Editora.class);
		} catch (IOException err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Editora", err.toString());
		}
		
		return editora;
	}*/
		
}
