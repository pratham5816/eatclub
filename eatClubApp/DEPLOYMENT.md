# Environment Variables Setup Guide

## For Local Development

1. Copy `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```

2. Fill in your actual secret values in `.env`

3. **NEVER commit `.env` to Git!** (already in `.gitignore`)

## For Production/Hosting

### Option 1: Heroku
Set environment variables in Heroku dashboard or CLI:
```bash
heroku config:set TWILIO_ACCOUNT_SID=your_value
heroku config:set TWILIO_AUTH_TOKEN=your_value
heroku config:set MY_SECRET_KEY=your_value
heroku config:set MY_API_TOKEN=your_value
```

### Option 2: AWS Elastic Beanstalk
Add environment variables in AWS Console:
1. Go to Configuration → Software
2. Add Environment Properties:
   - `TWILIO_ACCOUNT_SID`
   - `TWILIO_AUTH_TOKEN`
   - `MY_SECRET_KEY`
   - `MY_API_TOKEN`

### Option 3: Azure App Service
Set environment variables in Azure Portal:
```bash
az webapp config appsettings set --name yourapp --resource-group yourgroup \
  --settings TWILIO_ACCOUNT_SID=value TWILIO_AUTH_TOKEN=value MY_SECRET_KEY=value
```

### Option 4: Docker
Create a `.env.production` file and use it with Docker:
```bash
docker run --env-file .env.production yourapp
```

### Option 5: Render/Railway
Add environment variables in their dashboard under "Environment" tab.

## How to Use in Code

### Method 1: Using @Value annotation
```java
@Value("${myapp.mykey}")
private String secretKey;
```

### Method 2: Using Configuration class (Recommended)
```java
@Autowired
private AppConfig appConfig;

String key = appConfig.getSecretKey();
```

## Security Checklist

- ✅ `.env` is in `.gitignore`
- ✅ Never hardcode secrets in code
- ✅ Use environment variables for all sensitive data
- ✅ Different values for dev/staging/production
- ✅ Rotate keys regularly
- ✅ Share `.env.example` (without actual values) with team
