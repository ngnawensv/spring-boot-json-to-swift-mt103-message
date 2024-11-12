package cm.belrose.service;

import cm.belrose.model.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class PaymentConversionServiceImpl implements PaymentConversionService {
    ObjectMapper objectMapper = new ObjectMapper();
    String mt103Message = null;

    public String convertToMT103(MultipartFile file) {
        try {
            PaymentRequest paymentRequest = objectMapper.readValue(file.getBytes(), PaymentRequest.class);

            String senderBank = paymentRequest.getSender().getBank();
            String senderAccount = paymentRequest.getSender().getAccount();
            String receiverBank = paymentRequest.getReceiver().getBank();
            String receiverAccount = paymentRequest.getReceiver().getAccount();
            double amount = paymentRequest.getAmount();
            String currency = paymentRequest.getCurrency();
            String date = paymentRequest.getDate();
            String reference = paymentRequest.getReference();

            mt103Message = "{1:F01" + senderBank + "XXXX0000000000}"
                    + "{2:I103" + receiverBank + "XXXXN}"
                    + "{3:{108:" + reference + "}}"
                    + "{4:"
                    + ":20:" + reference
                    + ":23B:CRED"
                    + ":32A:" + date.replace("-", "") + currency + amount
                    + ":50K:" + "FOO CAPITAL MARKETS LP 99 WALL ST STE 9999 NEW YORK, NY 10005-3122"
                    + ":59:/AU351234567800123456789 FIRM LTD"
                    + ":71A:OUR-"
                    + "-}";
            log.info("mt103Message {}", mt103Message);
            return mt103Message;
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>>>>>>>Error parsing {}");
            return null;
        }

    }
}
